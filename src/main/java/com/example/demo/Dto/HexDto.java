package com.example.demo.Dto;

import com.example.demo.Model.HexData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HexDto extends HexData {
    @ApiModelProperty(value = "测试目标平台账号")
    private String userName;
    @ApiModelProperty(value = "测试目标平台密码")
    private String password;
    @ApiModelProperty(value = "测试目标平台")
    private int sendPlatform;
}
