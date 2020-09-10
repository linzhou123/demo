package com.example.demo.Controller;

import com.example.demo.Model.Api;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiRequestResultService;
import com.example.demo.Service.ApiService;
import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    ApiService apiService;
    @Resource
    ApiRequestResultService apiRequestResultService;

    /**
     * 新增api接口
     * */
    @PostMapping("/add")
    public ResponseInfo insertApi(@RequestBody Api api) {
        List<Api> apiList = apiService.findAllByName(api.getName());
        if (apiList.size() > 0) {
            return ResponseInfo.errorInfo(api.getName() + "已存在");
        }
        apiService.insertApi(api);
        return ResponseInfo.successInfo("");
    }

    /**
     * api分页接口
     * */
    @GetMapping("/page")
    public ResponseInfo apiPage(@RequestParam(value = "pageNum") int pageNum,@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "apiSuiteId",required = false) Integer apiSuiteId){
            return ResponseInfo.successInfo(apiService.findAllWithPage(pageNum,pageSize,apiSuiteId));
    }

    /**
     * api编写接口
     * */
    @PostMapping("/edit")
    public ResponseInfo updateApi(@RequestBody Api api) {
        try {
            apiService.updateApi(api);
            return ResponseInfo.successInfo("");
        } catch (Exception e) {
            return ResponseInfo.errorInfo(e.getMessage());

        }
    }

    @GetMapping("/list")
    public ResponseInfo findBySuiteId(@RequestParam Integer apiSuiteId){
        return ResponseInfo.successInfo(apiService.findBySuiteId(apiSuiteId));
    }

    /**
     * debug -api接口
     * */
    @PostMapping("/run")
    public ResponseInfo runApi(@RequestParam int apiId) {
        apiRequestResultService.insertApiRequestResult(apiId);
        return ResponseInfo.successInfo("");
    }
}
