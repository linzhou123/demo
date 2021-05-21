package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Env {
    private int id;
    @ApiModelProperty(value = "项目id")
    private int projectId;
    @ApiModelProperty(value = "环境变量名称")
    private String name ;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;
}
