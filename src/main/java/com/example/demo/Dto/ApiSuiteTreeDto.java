package com.example.demo.Dto;

import com.example.demo.Model.Api;
import com.example.demo.Model.ApiSuite;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiSuiteTreeDto extends ApiSuite {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "api集合")
    List<Api> apiList;
}
