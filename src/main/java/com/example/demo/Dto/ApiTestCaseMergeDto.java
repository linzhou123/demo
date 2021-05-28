package com.example.demo.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiTestCaseMergeDto {
    @ApiModelProperty(value = "测试用例集id")
    private int groupId;
    @ApiModelProperty(value = "测试用例ids")
    private List<Integer> testCaseIds;
}
