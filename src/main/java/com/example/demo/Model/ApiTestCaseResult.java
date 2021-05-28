package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiTestCaseResult implements Serializable {
    private int id;
    @ApiModelProperty(value = "测试用例id")
    private int testCaseId;
    @ApiModelProperty(value = "测试用例步骤总数")
    private int countResults;
    @ApiModelProperty(value = "测试用例步骤通过数量")
    private int passResults;
    @ApiModelProperty(value = "测试用例步骤失败数量")
    private int failedResults;
    @ApiModelProperty(value = "测试用例运行总时间")
    private int testRunTime;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;

}
