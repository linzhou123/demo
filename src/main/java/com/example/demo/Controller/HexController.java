package com.example.demo.Controller;

import com.example.demo.Model.HexData;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.HexDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Hex")
@Validated
@Api(tags = "hex流相关接口管理")
public class HexController {

    @Resource
    HexDataService hexDataService;

    @PostMapping("/add")
    @ApiOperation(value = "添加hex流相关数据接口")
    @ApiImplicitParam(name = "hexData", value = "hexData", required = true, dataType = "body")
    public ResponseInfo addHexData(@RequestBody HexData hexData) {
        hexDataService.insertHexData(hexData);
        return ResponseInfo.successInfo("");
    }

    @PostMapping("/debug")
    @ApiOperation(value = "执行一条hex流数据")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "hexId", name = "id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "username", value = "对应测试数据登录账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "对应测试数据登录密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "host", value = "所有hex发往的域名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "port", value = "所有hex发往的域名端口", required = false, dataType = "Integer"),
    })
    public ResponseInfo debugHexData(Integer id, String username, String password, String host, Integer port) {
        return ResponseInfo.successInfo(hexDataService.debugHexData(id, username, password, host, port));
    }

    @PostMapping("/run")
    @ApiOperation(value = "发送所有hex流")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "对应测试数据登录账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "对应测试数据登录密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "host", value = "所有hex发往的域名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "port", value = "所有hex发往的域名端口", required = false, dataType = "Integer"),
    })
    public ResponseInfo runHexData(String username, String password, String host, Integer port) {
        return ResponseInfo.successInfo(hexDataService.runHexData(username, password, host, port));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除hex接口")
    public ResponseInfo deleteApi(@PathVariable(value = "id") int id) {
        hexDataService.deleteHex(id);
        return ResponseInfo.successInfo("");
    }

    @GetMapping("/page")
    @ApiOperation(value = "hex数据分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页长度", required = true, dataType = "int")
    }
    )
    public ResponseInfo getHexPage(int pageNum, int pageSize) {
        return ResponseInfo.successInfo(hexDataService.getHexPage(pageNum, pageSize));
    }

}
