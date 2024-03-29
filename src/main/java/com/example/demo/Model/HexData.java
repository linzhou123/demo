package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HexData implements Serializable {
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "ip地址")
    private String host;
    @ApiModelProperty(value = "端口")
    private int port;
    @ApiModelProperty(value = "hex流名称")
    private String name;
    @ApiModelProperty(value = "对应hex流设备code")
    private String deviceName;
    @ApiModelProperty(value = "hex流发送内容")
    private String hexContent;
    @ApiModelProperty(value = "数据类型")
    private int dataType;
    @ApiModelProperty(value = "设备类型")
    private int deviceType;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;

}
