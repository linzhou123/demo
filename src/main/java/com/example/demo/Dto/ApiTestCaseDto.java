package com.example.demo.Dto;

import com.example.demo.Model.ApiTestCase;
import com.example.demo.Model.ApiTestCaseStep;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiTestCaseDto extends ApiTestCase {
    @ApiModelProperty(value = "测试步骤集合")
    List<ApiTestCaseStep> apiTestCaseStepList;
}
