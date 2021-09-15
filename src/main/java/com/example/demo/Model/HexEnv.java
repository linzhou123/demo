package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HexEnv implements Serializable {
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "ip地址")
    private String host;
    @ApiModelProperty(value = "端口")
    private int port;
    @ApiModelProperty(value = "hex_env名称")
    private String name;
    @ApiModelProperty(value = "发送平台")
    private int sendPlatform;//0为大平台 1为老消控
    @ApiModelProperty(value = "发送平台账号")
    private String platformUser;
    @ApiModelProperty(value = "发送平台密码")
    private String platformPassword;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;
}
