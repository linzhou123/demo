package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Header implements Serializable {
    @ApiModelProperty(value = "头部的key")
    private String key;
    @ApiModelProperty(value = "头部的value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
