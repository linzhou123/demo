package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Header implements Serializable {
    @ApiModelProperty(value = "头部的key")
    private String key;
    @ApiModelProperty(value = "头部的value")
    private String value;

    private static final long serialVersionUID = 1L;

}
