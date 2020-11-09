package com.example.demo.Controller;

import com.example.demo.Dto.ApiTestCaseMergeDto;
import com.example.demo.Model.ApiTestCaseGroup;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiTestCaseGroupService;
import com.example.demo.Service.ApiTestCaseMergeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/apiTestGroup")
public class ApiTestGroupController {

    @Resource
    ApiTestCaseGroupService apiTestCaseGroupService;

    @Resource
    ApiTestCaseMergeService apiTestCaseMergeService;
    /**
     * 新增测试用例集
     * */
    @PostMapping("/add")
    public ResponseInfo addGroup (@RequestBody ApiTestCaseGroup apiTestCaseGroup){
        apiTestCaseGroupService.insertApiTestCaseGroup(apiTestCaseGroup);
        return ResponseInfo.successInfo("");
    }

    @PostMapping("/addCaseToGroup")
    public ResponseInfo addCaseToGroup(@RequestBody ApiTestCaseMergeDto apiTestCaseMergeDto){
        apiTestCaseMergeService.insertApiTestCaseMerge(apiTestCaseMergeDto);
        return ResponseInfo.successInfo("");
    }

    @PostMapping("/run")
    public ResponseInfo runTestGroup(@RequestParam int testGroupId){
        apiTestCaseGroupService.runTestGroup(testGroupId);
        return ResponseInfo.successInfo("");
    }

}
