package com.example.demo.Model.ApiModel;

import java.io.Serializable;

public class ResultAssert implements Serializable {
    private String checkList;
    private String value;
    private boolean result;

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

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
