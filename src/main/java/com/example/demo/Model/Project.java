package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Project implements Serializable {
    @ApiModelProperty(value = "项目id")
    private int id;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "详细描述")
    private String description;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;
}
