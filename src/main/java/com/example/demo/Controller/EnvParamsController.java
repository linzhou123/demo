package com.example.demo.Controller;

import com.example.demo.Model.EnvParams;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.EnvParamsService;
import com.example.demo.utils.DateToStamp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/envParams")
@Api(tags = "环境变量参数管理")
public class EnvParamsController {
    @Resource
    EnvParamsService envParamsService;

    @PostMapping("/add")
    @ApiOperation(value = "环境变量参数添加接口")
    public ResponseInfo add(@RequestBody  EnvParams envParams){
        envParams.setCreateTime(DateToStamp.getTimeStap());
        envParams.setUpdateTime(DateToStamp.getTimeStap());
        envParamsService.insert(envParams);
        return ResponseInfo.successInfo("");
    }
}
