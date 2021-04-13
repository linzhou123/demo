package com.example.demo.Controller;

import com.example.demo.Model.Api;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiRequestResultService;
import com.example.demo.Service.ApiService;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/api")
@io.swagger.annotations.Api(value = "/api", tags = "api接口")
public class ApiController {

    @Resource
    ApiService apiService;
    @Resource
    ApiRequestResultService apiRequestResultService;

    /**
     * 新增api接口
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加api接口")
    public ResponseInfo insertApi(@RequestBody Api api) {
        List<Api> apiList = apiService.findAllByName(api.getName());
        if (apiList.size() > 0) {
            return ResponseInfo.errorInfo("接口:" + api.getName() + "已存在");
        }
        apiService.insertApi(api);
        return ResponseInfo.successInfo("");
    }

    /**
     * api分页接口
     */
    @GetMapping("/page")
    @ApiOperation(value = "api分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页长度", required = true, dataType = "int"),
            @ApiImplicitParam(name = "apiSuiteId", value = "api类别的id", required = false, dataType = "int"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = false,dataType = "int")
    })
    public ResponseInfo apiPage(int pageNum,
                                int pageSize,
                                Integer apiSuiteId,Integer projectId) {
        return ResponseInfo.successInfo(apiService.findAllWithPage(pageNum, pageSize, apiSuiteId,projectId));
    }

    /**
     * api编写接口
     */
    @PostMapping("/edit")
    @ApiOperation(value = "api更新接口")
    public ResponseInfo updateApi(@RequestBody Api api) {
        try {
            apiService.updateApi(api);
            return ResponseInfo.successInfo("");
        } catch (Exception e) {
            return ResponseInfo.errorInfo(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除api接口")
    public ResponseInfo deleteApi(@PathVariable(value = "id")int id){
        apiService.deleteApi(id);
        return ResponseInfo.successInfo("");
    }

    @GetMapping("/list")
    @ApiOperation(value = "api列表接口")
    @ApiImplicitParam(name ="apiSuiteId",value = "api类别的id",required = true,dataType = "int")
    public ResponseInfo findBySuiteId(Integer apiSuiteId) {
        return ResponseInfo.successInfo(apiService.findBySuiteId(apiSuiteId));
    }

    /**
     * debug -api接口
     */
    @PostMapping("/run")
    @ApiOperation(value = "api调试接口")
    @ApiImplicitParam(name ="id",value = "apiId",required = true,dataType = "int")
    public ResponseInfo runApi(int id) {
        return ResponseInfo.successInfo(apiRequestResultService.insertApiRequestResult(id));
    }

    @GetMapping("/details")
    @ApiOperation(value = "获取api详情接口", notes = "通过id获取api详情")
    @ApiImplicitParam(name ="id",value = "apiId",required = true,dataType = "int")
    public ResponseInfo getApiDetails(int id) {
        return ResponseInfo.successInfo(apiService.findById(id));
    }
}
