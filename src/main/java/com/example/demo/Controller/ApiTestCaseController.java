package com.example.demo.Controller;


import com.example.demo.Model.ApiTestCase;
import com.example.demo.Model.ApiTestCaseStep;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiTestCaseService;
import com.example.demo.Service.ApiTestCaseStepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/TestCase")
@Api(tags = "测试用例管理")
public class ApiTestCaseController {

    @Resource
    private ApiTestCaseService apiTestCaseService;
    @Resource
    private ApiTestCaseStepService apiTestCaseStepService;

    @PostMapping("/add")
    @ApiOperation(value = "测试用例添加接口")
    public ResponseInfo insertApiTestCase(@RequestBody ApiTestCase apiTestCase) {
        try {
            if (apiTestCase.getName().equals("")) {
                return ResponseInfo.errorInfo("测试用例名称不能为空");
            } else {
                apiTestCaseService.insertApiTestCase(apiTestCase);
                return ResponseInfo.successInfo("");
            }
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseInfo.errorInfo(e.toString());
        }
    }

    /**
     * 通过id查找TestCase
     */
    @GetMapping("/findById")
    @ApiOperation(value = "根据id获取测试用例")
    public ResponseInfo findById(@RequestParam(value = "id") Integer id) {
        return ResponseInfo.successInfo(apiTestCaseService.findById(id));
    }

    @GetMapping("/Step/list")
    @ApiOperation(value = "根据用例id获取测试用例步骤")
    @ApiImplicitParam(name = "testCaseId", value = "测试用例id", required = true, dataType = "int")
    public ResponseInfo findStepListByTestCaseId(int testCaseId) {
        return ResponseInfo.successInfo(apiTestCaseStepService.findStepListByTestCaseId(testCaseId));
    }

    @PostMapping("/Step/update")
    @ApiOperation(value = "更新用例步骤")
    public ResponseInfo upateStepInCase(@RequestBody List<ApiTestCaseStep> apiTestCaseSteps) {
        apiTestCaseStepService.apiTestCaseStepEdit(apiTestCaseSteps);
        return ResponseInfo.successInfo("");
    }

    @DeleteMapping("/Step/delete/{id}")
    @ApiOperation(value = "刪除用例步驟")
    public ResponseInfo deleteStepInCase(@PathVariable("id") int stepId) {
        if (stepId != 0) {
            apiTestCaseStepService.apiTestCaseStepDelete(stepId);
        }
        return ResponseInfo.successInfo("");
    }

    @PostMapping("/Step/deletes")
    @ApiOperation(value = "批量删除测试用例接口")
    @ApiImplicitParam(name = "ids", value = "id列表", required = true, dataType = "List")
    public ResponseInfo deleteStepsInCase(List<Integer> ids) {
        if (ids.size() > 0) {
            apiTestCaseStepService.apiTestCaseStepsDelete(ids);
            return ResponseInfo.successInfo("");
        } else {
            return ResponseInfo.errorInfo("ids不能为空");
        }
    }

    @GetMapping("/page")
    @ApiOperation(value = "测试用例分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页长度", required = true, dataType = "int"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "测试用例名称", required = false, dataType = "String"),
    })
    public ResponseInfo findWithPage(int pageNum, int pageSize, Integer projectId, String name) {
        return ResponseInfo.successInfo(apiTestCaseService.findByPage(pageNum, pageSize, projectId, name));
    }

    @PostMapping("/run")
    @ApiOperation(value = "执行测试用例接口")
    public ResponseInfo runTestCase(@RequestParam Integer testCaseId) {
        return ResponseInfo.successInfo(apiTestCaseStepService.runStep(testCaseId));
    }

    @PostMapping("/addApiStep")
    @ApiOperation(value = "添加测试用例步骤接口")
    public ResponseInfo addApiStep(@RequestBody List<ApiTestCaseStep> apiTestCaseStepList) {
        apiTestCaseStepService.insertStepToTestCase(apiTestCaseStepList);
        return ResponseInfo.successInfo("");
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "测试用例删除接口")
    public ResponseInfo deleteTestCase(@PathVariable("id") int testCaseId) {
        apiTestCaseService.testCaseDelete(testCaseId);
        return ResponseInfo.successInfo("");
    }

}
