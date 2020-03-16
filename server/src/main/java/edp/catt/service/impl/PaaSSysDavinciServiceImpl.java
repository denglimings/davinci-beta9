package edp.catt.service.impl;

import com.alibaba.fastjson.JSON;
import edp.catt.service.PassSysDavinciService;
import edp.catt.utils.StringUtil;
import edp.core.exception.ServerException;
import edp.davinci.core.common.ResultMap;
import edp.davinci.dao.UserMapper;
import edp.davinci.dto.projectDto.ProjectInfo;
import edp.davinci.dto.userDto.UserRegist;
import edp.davinci.model.Dashboard;
import edp.davinci.model.DashboardPortal;
import edp.davinci.model.Display;
import edp.davinci.model.User;
import edp.davinci.service.DashboardPortalService;
import edp.davinci.service.DashboardService;
import edp.davinci.service.DisplayService;
import edp.davinci.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("passSysDavinciService")
public class PaaSSysDavinciServiceImpl implements PassSysDavinciService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DashboardPortalService dashboardPortalService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private DisplayService displayService;


    @Override
    public Map<String, String> batchInsertUser(Map<String, String> map) throws Exception {
        String accounts = MapUtils.getString(map, "accounts");
        //检查已注册的账号
        String[] accountList = accounts.split(",");
        List<String> insertAccount = new ArrayList<>();
        List<String> failAccountList = new ArrayList<>();
        List<Long> successUserIdList = new ArrayList<>();
        Map<String, String> resultMap = new HashMap<>(2);
        if (accountList.length > 0) {
            for (String account : accountList) {
                boolean exist = isExist(account, null);
                if (exist) {
                    failAccountList.add(account);
                } else {
                    insertAccount.add(account);
                }
            }
        }
        if (insertAccount.size() > 0) {
            for (String userName : insertAccount) {
                UserRegist userRegist = new UserRegist();
                userRegist.setUsername(userName);
                userRegist.setEmail(userName + "@example.com");
                //密码加密
                userRegist.setPassword(userName + "@Davinci");
                try {
                    successUserIdList.add(regist(userRegist).getId());
                } catch (ServerException e) {
                    failAccountList.add(userName);
                }
            }
        }
        resultMap.put("failAccountList", JSON.toJSONString(failAccountList));
        resultMap.put("successUserIdList", JSON.toJSONString(successUserIdList));
        return resultMap;
    }


    /**
     * 用户注册接口
     *
     * @param userRegist
     * @return
     */
    @Transactional
    public synchronized User regist(UserRegist userRegist) throws ServerException {

        User user = new User();
        //密码加密
        userRegist.setPassword(BCrypt.hashpw(userRegist.getPassword(), BCrypt.gensalt()));
        BeanUtils.copyProperties(userRegist, user);
        //添加用户
        user.setActive(true);
        int insert = userMapper.insert(user);
        if (insert > 0) {
            return user;
        } else {
            log.info("regist fail: {}", userRegist.toString());
            throw new ServerException("regist fail: unspecified error");
        }
    }

    /**
     * 用户是否存在
     *
     * @param name
     * @param
     * @return
     */

    public synchronized boolean isExist(String name, Long id) {
        Long userId = userMapper.getIdByName(name);
        if (null != id && null != userId) {
            return !id.equals(userId);
        }
        return null != userId && userId.longValue() > 0L;
    }


    @Override
    public ResponseEntity getDavDavinciViz(User user, Map<String, String> param) {
        List<ProjectInfo> projects = projectService.getProjects(user);
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        //根据每个projectId获取Viz
        if (projects != null && projects.size() > 0){
            for (ProjectInfo project : projects) {
                Map<String, Object> projectMap = new HashMap<>();
                List<Map<String, Object>> vizMapList = new ArrayList<>();
                Map<String, Object> displaysMap = new HashMap<>(3);
                Map<String, Object> dashboardPortalMap = new HashMap<>(3);
                //projectID
                Long projectId = project.getId();
                projectMap.put("name", project.getName());
                projectMap.put("id", projectId);
                projectMap.put("children", vizMapList);
                vizMapList.add(displaysMap);
                vizMapList.add(dashboardPortalMap);

                List<Map<String, Object>> dashboardPortalsList = new ArrayList<>();
                dashboardPortalMap.put("name","dashboardPortals");
                dashboardPortalMap.put("selectType",1);
                dashboardPortalMap.put("children",dashboardPortalsList);

                List<Map<String, Object>> displaysList = new ArrayList<>();
                displaysMap.put("name","displays");
                displaysMap.put("selectType",2);
                displaysMap.put("children",displaysList);

                //1获取 dashboardPortals
                List<DashboardPortal> dashboardPortals = dashboardPortalService.getDashboardPortals(projectId, user);
                if (dashboardPortals != null && dashboardPortals.size() > 0) {
                    for (DashboardPortal dashboardPortal : dashboardPortals) {
                        Long id = dashboardPortal.getId();
                        //每个dashboardPortal 对应的具体编辑实例
                        Map<String,Object> dashboardPortalsMap = new HashMap<>(7);
                        dashboardPortalsMap.put("avatar",dashboardPortal.getAvatar());
                        dashboardPortalsMap.put("description",dashboardPortal.getDescription());
                        dashboardPortalsMap.put("id",id);
                        dashboardPortalsMap.put("name",dashboardPortal.getName());
                        dashboardPortalsMap.put("projectId",dashboardPortal.getProjectId());
                        dashboardPortalsMap.put("publish",dashboardPortal.getPublish());
                        //todo 多节点情况是否树形返回
                        //每个viz 对应的DashboardPortal
                        List<Map<String, Object>> singleDashboardPortalsMap = new ArrayList<>();
                        List<Dashboard> singleDashboardPortalList = dashboardService.getDashboards(id, user);
                        if (singleDashboardPortalList != null && singleDashboardPortalList.size() > 0) {
                            for (Dashboard dashboard : singleDashboardPortalList) {
                                short type = dashboard.getType();
                                //type = 0 表示目录
                                if (StringUtil.checkObj(type) && type == 1) {
                                    //根据stringObjectMap 的 id 获取分享信息
                                    Long dashboardId = dashboard.getId();
                                    String shareToken = dashboardService.shareDashboard(dashboardId, null, user);
                                    if (StringUtil.checkStr(shareToken)) {
                                        Map<String, Object> stringObjectMap = new HashMap<>(6);
                                        stringObjectMap.put("selectType", 1);
                                        stringObjectMap.put("flagName", +type + "-" + dashboardId);
                                        stringObjectMap.put("shareToken", shareToken);
                                        stringObjectMap.put("projectId", projectId);
                                        stringObjectMap.put("name", dashboard.getName());
                                        stringObjectMap.put("id", dashboardId);
                                        singleDashboardPortalsMap.add(stringObjectMap);
                                    }
                                }
                            }
                        }
                        dashboardPortalsMap.put("children",singleDashboardPortalsMap);
                        dashboardPortalsList.add(dashboardPortalsMap);
                    }

                }
                //2.获取display
                List<Display> displayList = displayService.getDisplayListByProject(projectId, user);
                if (displayList != null && displayList.size() > 0){
                    try {
                        //每个viz 对应的Displays
                        for (Display display : displayList) {
                            Long id = display.getId();
                            String shareToken = displayService.shareDisplay(id, user, null);
                            if (StringUtil.checkStr(shareToken)) {
                                Map<String, Object> itemMap = new HashMap<>(6);
                                itemMap.put("selectType", 2);
                                itemMap.put("flagName",  "2-" + id);
                                itemMap.put("shareToken", shareToken);
                                itemMap.put("projectId", projectId);
                                itemMap.put("name", display.getName());
                                itemMap.put("id",  id);
                                displaysList.add(itemMap);
                            }
                        }
                    } catch (Exception e) {
                        log.info(e.getMessage());
                        ResultMap resultMap = new ResultMap().fail().message(e.getMessage());
                        return ResponseEntity.status(resultMap.getCode()).body(resultMap);
                    }
                }
                resultMapList.add(projectMap);
            }
        }
        return ResponseEntity.ok(new ResultMap().success().payload(resultMapList));
    }

}
