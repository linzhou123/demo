package com.example.demo.Controller;

import com.example.demo.Model.HexEnv;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.HexEnvService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hexEnv")
@Api(tags = "hex环境变量接口")
public class HexEnvController {

    @Resource
    HexEnvService hexEnvService;

    @PostMapping("/add")
    @ApiOperation(value = "添加hex环境变量接口")
    public ResponseInfo add(@RequestBody HexEnv hexEnv) {
        hexEnvService.insertHexEnv(hexEnv);
        return ResponseInfo.successInfo("");
    }
    @GetMapping("/hexDetails")
    @ApiImplicitParam(name = "id",value = "id",required = true ,dataType = "int")
    public ResponseInfo hexDetails(int id){
        return ResponseInfo.successInfo(hexEnvService.getHexEnvById(id));
    }
}
