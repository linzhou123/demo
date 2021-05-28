package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiTestCaseGroup implements Serializable {
    @ApiModelProperty(value = "测试用例集id")
    private int id;
    @ApiModelProperty(value = "项目id")
    private int projectId;
    @ApiModelProperty(value = "测试用例集名称")
    private String name;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;
}
