package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiTestCaseGroupResult implements Serializable {
    private int id;
    @ApiModelProperty(value = "测试用例集id")
    private int testCaseGroupId;
    @ApiModelProperty(value = "用例集测试用例总条数")
    private int countResult;
    @ApiModelProperty(value = "用例集测试用例成功条数")
    private int passResult;
    @ApiModelProperty(value = "用例集测试用例失败条数")
    private int failedResult;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;
}
