package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultAssert implements Serializable {
    @ApiModelProperty(value = "校验jsonPath表达式")
    private String checkList;
    @ApiModelProperty(value = "校验预期值")
    private String value;
    @ApiModelProperty(value = "response返回值")
    private String realValue;
    @ApiModelProperty(value = "判断结果是否成功")
    private boolean result;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "{ checkList:'" + checkList + "', value:'" + value + "',realValue：’" + realValue + "',result:'" + result + "'}";
    }
}
