package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParameterExtraction {
    @ApiModelProperty(value = "提取表达式类型")
    private int dataType;
    @ApiModelProperty(value = "表达式内容")
    private String expression;
    @ApiModelProperty(value = "提取参数后名称")
    private String extractName;
}
