package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiTestCaseMerge implements Serializable {
    @ApiModelProperty(value = "测试步骤关联表id")
    private int id;
    @ApiModelProperty(value = "测试用例集id")
    private int testCaseGroupId;
    @ApiModelProperty(value = "测试用例id")
    private int apiTestCaseId;

    private static final long serialVersionUID = 1L;
}
