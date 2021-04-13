package com.example.demo.Model.ApiModel;

import java.io.Serializable;

public class ResultAssert implements Serializable {
    private String checkList;
    private String value;
    private String realValue;
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

    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString(){
        return "{ checkList:'"+checkList+"', value:'"+value+"',realValue：’"+realValue+"',result:'"+result+"'}";
    }
}
