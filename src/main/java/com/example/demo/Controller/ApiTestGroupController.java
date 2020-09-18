package com.example.demo.Controller;

import com.example.demo.Model.ApiTestCaseGroup;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiTestCaseGroupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/apiTestGroup")
public class ApiTestGroupController {

    @Resource
    ApiTestCaseGroupService apiTestCaseGroupService;

    /**
     * 新增测试用例集
     * */
    @PostMapping("/add")
    public ResponseInfo addGroup (@RequestBody ApiTestCaseGroup apiTestCaseGroup){
        return ResponseInfo.successInfo(apiTestCaseGroupService.insertApiTestCaseGroup(apiTestCaseGroup));
    }

    @PostMapping("/addCaseToGroup")
    public ResponseInfo addCaseToGroup(){
        return null;
    }

}
