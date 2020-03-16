package edp.catt.service;

import edp.davinci.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PassSysDavinciService  {
    Map<String,String> batchInsertUser(Map<String, String> map) throws Exception;

    ResponseEntity getDavDavinciViz(User user, Map<String, String> param);
}
