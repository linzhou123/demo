package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class SendHexRecord implements Serializable {
    private int id;
    @ApiModelProperty(value = "目标url")
    private String url;
    @ApiModelProperty(value = "发送hex内容")
    private String hexContent;
    @ApiModelProperty(value = "系统返回内容")
    private String sysBackContent;
    @ApiModelProperty(value = "判断是否通过")
    private Boolean isPass;
    @ApiModelProperty(value = "创建时间")
    private int createTime;

    private static final long serialVersionUID = 1L;
}
