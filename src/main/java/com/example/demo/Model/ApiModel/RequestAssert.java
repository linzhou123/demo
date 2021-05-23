package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class RequestAssert implements Serializable {
    @ApiModelProperty(value = "校验jsonPath表达式")
    private String checkList;
    @ApiModelProperty(value = "校验预期值")
    private String value;

    private static final long serialVersionUID = 1L;

}
