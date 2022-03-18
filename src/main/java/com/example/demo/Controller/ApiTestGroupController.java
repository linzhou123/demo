package com.example.demo.Controller;

import com.example.demo.Dto.ApiTestCaseMergeDto;
import com.example.demo.Model.ApiTestCaseGroup;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiTestCaseGroupService;
import com.example.demo.Service.ApiTestCaseMergeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/apiTestGroup")
@Api(tags = "测试用例集管理")
public class ApiTestGroupController {

    @Resource
    ApiTestCaseGroupService apiTestCaseGroupService;

    @Resource
    ApiTestCaseMergeService apiTestCaseMergeService;

    /**
     * 新增测试用例集
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加测试用例集")
    public ResponseInfo addGroup(@RequestBody ApiTestCaseGroup apiTestCaseGroup) {
        apiTestCaseGroupService.insertApiTestCaseGroup(apiTestCaseGroup);
        return ResponseInfo.successInfo("");
    }

    /**
     * 添加测试用例至测试用例集
     */
    @PostMapping("/addCaseToGroup")
    @ApiOperation(value = "添加测试用例至测试用例集")
    public ResponseInfo addCaseToGroup(@RequestBody ApiTestCaseMergeDto apiTestCaseMergeDto) {
        apiTestCaseMergeService.insertApiTestCaseMerge(apiTestCaseMergeDto);
        return ResponseInfo.successInfo("");
    }


    /**
     * 从测试用例集中删除测试用例
     */
    @DeleteMapping("/deleteCase/{id}")
    @ApiOperation(value = "测试用例集中删除测试用例")
    public ResponseInfo deleteCase(@PathVariable("id") Integer id) {
        return ResponseInfo.successInfo("");
    }


    /**
     * 运行用例集-获取结果集
     */
    @PostMapping("/run")
    @ApiOperation(value = "运行测试用例集")
    public ResponseInfo runTestGroup(@RequestParam int testGroupId,@RequestParam int envId) {
        apiTestCaseGroupService.runTestGroup(testGroupId,envId);
        return ResponseInfo.successInfo("");
    }

    /**
     * 测试用例集展示列表
     * */
    @GetMapping("/list")
    @ApiOperation(value = "测试用例集展示列表")
    public ResponseInfo list(@RequestParam int projectId,@RequestParam(required = false) String groupName){
        return ResponseInfo.successInfo(apiTestCaseGroupService.apiTestCaseGroupList(projectId,groupName));
    }

    @GetMapping("/caseList")
    @ApiOperation(value = "测试用例展示")
    public ResponseInfo caseListByGroupId(@RequestParam int testGroupId){
        return ResponseInfo.successInfo(apiTestCaseGroupService.caseListByGroupId(testGroupId));
    }

}
