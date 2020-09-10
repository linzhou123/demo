package com.example.demo.Controller;


import com.example.demo.Model.ApiTestCase;
import com.example.demo.Model.ApiTestCaseStep;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiTestCaseService;
import com.example.demo.Service.ApiTestCaseStepService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/TestCase")
public class ApiTestCaseController {

    @Resource
    private ApiTestCaseService apiTestCaseService;
    @Resource
    private ApiTestCaseStepService apiTestCaseStepService;

    @PostMapping("/add")
    public ResponseInfo insertApiTestCase(@RequestBody ApiTestCase apiTestCase){
        apiTestCaseService.insertApiTestCase(apiTestCase);
        return ResponseInfo.successInfo("");
    }
    /**
     * 通过id查找TestCase
     * */
    @GetMapping("/findById")
    public ResponseInfo findById(@RequestParam(value = "id") Integer id){
        return ResponseInfo.successInfo(apiTestCaseService.findById(id));
    }

    @GetMapping("/page")
    public ResponseInfo findWithPage(@RequestParam(value = "pageNum") int pageNum ,@RequestParam(value = "pageSize") int pageSize){
        return ResponseInfo.successInfo(apiTestCaseService.findByPage(pageNum,pageSize));
    }

    @PostMapping("/run")
    public ResponseInfo runTestCase(@RequestParam Integer testCaseId){
        apiTestCaseStepService.runStep(testCaseId);
        return ResponseInfo.successInfo("");
    }

    @PostMapping("/addApiStep")
    public ResponseInfo addApiStep(@RequestBody List<ApiTestCaseStep> apiTestCaseStepList){
        apiTestCaseStepService.insertStepToTestCase(apiTestCaseStepList);
        return ResponseInfo.successInfo("");
    }
}
