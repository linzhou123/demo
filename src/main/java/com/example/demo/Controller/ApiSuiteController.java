package com.example.demo.Controller;

import com.example.demo.Model.ApiSuite;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiSuiteService;
import com.example.demo.Service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/apiSuite")
@Api(tags = "api类别管理")
public class ApiSuiteController {
    @Resource
    private ApiSuiteService apiSuiteService;
    @Resource
    private ProjectService projectService;

    @PostMapping("/add")
    @ApiOperation(value = "添加api类别")
    public ResponseInfo insertApiSuite(@RequestBody ApiSuite apiSuite) {
        List<ApiSuite> apiSuites = apiSuiteService.findAllByName(apiSuite.getName());
        if (apiSuites.size() > 0) {
            return ResponseInfo.errorInfo("apiSuiteName已重复");
        }
        apiSuiteService.insertApiSuite(apiSuite);
        return ResponseInfo.successInfo("");
    }

    /**
     * 更新apiSuite
     */
    @PostMapping("/edit")
    @ApiOperation(value = "更新测试用例类别")
    public ResponseInfo updateApiSuite(@RequestBody ApiSuite apiSuite) {
        ApiSuite apiSuites = apiSuiteService.findAllById(apiSuite.getId());
        if (apiSuites.getId() == 0) {
            return ResponseInfo.errorInfo("apiSuite不存在");
        }
        apiSuiteService.updateApiSuite(apiSuite);
        return ResponseInfo.successInfo("");
    }

    /**
     * 删除apiSuite
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除测试用例类别")
    public ResponseInfo deleteApiSuiteById(@PathVariable(value = "id") int apiSuiteId) {
        apiSuiteService.deleteById(apiSuiteId);
        return ResponseInfo.successInfo("");
    }
    /**
     * apiSuite 分页接口
     */
    @GetMapping("/page")
    @ApiOperation(value = "测试用例分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value = "页数",required = true,dataType = "int"),
            @ApiImplicitParam(name="pageSize",value = "每页长度",required = true,dataType = "int"),
            @ApiImplicitParam(name="projetctId",value = "项目id",required = false,dataType = "int"),
    })
    public ResponseInfo finAllWithPage(int pageNum,
                                       int pageSize,
                                       int projetctId) {
        return ResponseInfo.successInfo(apiSuiteService.findAllWithPage(pageNum, pageSize,projetctId));
    }
    /**
     * api详情获取接口
     * */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取api详情接口")
    public ResponseInfo findById(@PathVariable(value = "id") int apiSuiteId){
        return ResponseInfo.successInfo(apiSuiteService.findAllById(apiSuiteId));
    }
    @GetMapping("/list")
    @ApiOperation(value = "根据项目id获取api类别列表")
    public ResponseInfo findByProjectId(@RequestParam(value = "projectId") int projectId){
        return ResponseInfo.successInfo(apiSuiteService.findByProjectId(projectId));
    }
    /**api分类树状图接口*/
    @GetMapping("/listTree")
    @ApiOperation(value = "根据项目id获取api类别树")
    public ResponseInfo findTreeDtoByProjectId(@RequestParam(value = "projectId") int projectId){
        return ResponseInfo.successInfo(apiSuiteService.findTreeDtoByProjectId(projectId));
    }
}

