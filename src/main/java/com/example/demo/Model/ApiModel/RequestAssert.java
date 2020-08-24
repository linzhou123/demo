package com.example.demo.Model.ApiModel;

import java.io.Serializable;

public class RequestAssert implements Serializable {
    private String checkList;
    private String value;

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
