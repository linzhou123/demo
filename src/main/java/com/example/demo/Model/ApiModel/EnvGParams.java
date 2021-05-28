package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EnvGParams {
    @ApiModelProperty(value = "环境id")
    private int envId;
    @ApiModelProperty(value = "对应环境id的值")
    private String envValue;

    private static final long serialVersionUID = 1L;
}
