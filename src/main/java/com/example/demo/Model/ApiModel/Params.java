package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Params implements Serializable {
    @ApiModelProperty(value = "参数名称")
    private String key;
    @ApiModelProperty(value = "参数值")
    private String value;

    private static final long serialVersionUID = 1L;

}
