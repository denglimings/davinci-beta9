package edp.catt.controller;

import edp.catt.service.PassSysDavinciService;
import edp.core.annotation.AuthIgnore;
import edp.core.annotation.CurrentUser;
import edp.davinci.core.common.Constants;
import edp.davinci.core.common.ResultMap;
import edp.davinci.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * PaaS 同步Davinci 控制层
 *
 */
@Api(value = "/users", tags = "users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiResponses(@ApiResponse(code = 404, message = "user not found"))
@RestController
@RequestMapping(value = Constants.BASE_API_PATH + "/paas2Davinci", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class PaaSSynDavinciController {

    @Autowired
    private PassSysDavinciService passSysDavinciService;

    /**
     * 用户注册
     *
     * @param map
     *
     * @return
     */
    @ApiOperation(value = "batch insert user")
    @AuthIgnore
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity regist( @RequestBody Map<String,String> map ) {
        String accounts = MapUtils.getString(map, "accounts");
        if (accounts != null  && !"".equals(accounts)){
            Map<String, String> resultMaps = null;
            try {
                 resultMaps = passSysDavinciService.batchInsertUser(map);
            } catch (Exception e) {
                e.printStackTrace();
                ResultMap resultMap = new ResultMap().fail().message(e.getMessage());
                return ResponseEntity.status(resultMap.getCode()).body(resultMap);
            }
            return ResponseEntity.ok(new ResultMap().success().payload(resultMaps));
        }else {
            ResultMap resultMap = new ResultMap().fail().message("不存在参数：accounts");
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
    }

    /**
     * 获取个人工作台Davinci可选Dashboard和Display
     *
     * @param param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取个人工作台Davinci可选Dashboard和Display")
    @RequestMapping(value = { "/workbench/davinci/getViz" }, method = { RequestMethod.POST })
    public ResponseEntity getDavDavinciViz(@ApiIgnore @CurrentUser User user,@RequestBody Map<String,String> param)  {
        try {
            return passSysDavinciService.getDavDavinciViz(user,param);
        } catch (Exception e) {
            e.printStackTrace();
            ResultMap resultMap = new ResultMap().fail().message(e.getMessage());
            return ResponseEntity.status(resultMap.getCode()).body(resultMap);
        }
    }
}
