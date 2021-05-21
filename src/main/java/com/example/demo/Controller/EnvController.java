package com.example.demo.Controller;

import com.example.demo.Model.Env;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.EnvService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@RestController
@RequestMapping("/env")
@Api(tags = "环境变量管理")
public class EnvController {
    @Resource
    EnvService envService;

    @PostMapping("/add")
    @ApiOperation(value = "新增环境变量")
    public ResponseInfo insert(@RequestBody  Env env){
        envService.insert(env);
        return ResponseInfo.successInfo("");
    }
    @GetMapping("/getDials")
    @ApiOperation(value = "获取环境变量详情")
    @ApiImplicitParam(name = "id",value = "环境变量id",required = true,dataType = "int")
    public  ResponseInfo getDials(int id){
        return ResponseInfo.successInfo(envService.selectById(id));
    }
    @GetMapping("/getAllByProjectId")
    @ApiOperation(value = "根据项目获取所有环境变量列表")
    @ApiImplicitParam(name = "projectId",value = "项目id",required = true,dataType = "id")
    public ResponseInfo getAllByProjectId(int projectId){
        return ResponseInfo.successInfo(envService.selectByProjectId(projectId));
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新环境变量")
    public ResponseInfo updateEnv(@RequestBody Env env){
        envService.updateEnv(env);
        return ResponseInfo.successInfo("");
    }

}
