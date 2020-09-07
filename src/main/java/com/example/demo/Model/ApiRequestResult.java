package com.example.demo.Model;

import com.example.demo.Model.ApiModel.Header;
import com.example.demo.Model.ApiModel.Params;
import com.example.demo.Model.ApiModel.ResultAssert;

import java.io.Serializable;
import java.util.List;

public class ApiRequestResult implements Serializable {
    private int id;
    private int apiId;
    private String apiName;
    private int apiTestCaseId;
    private int apiTestCaseStepId;
    private List<Header> requestHeader;
    private List<Params> requestParams;
    private String Method;
    private String URL;
    private String resultBody;
    private int resultStatus;
    private int resultTime;
    private List<ResultAssert> resultAssert;
    private boolean resultIsPass;
    private int creatTime;
    private int updateTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public int getApiTestCaseId() {
        return apiTestCaseId;
    }

    public void setApiTestCaseId(int apiTestCaseId) {
        this.apiTestCaseId = apiTestCaseId;
    }

    public int getApiTestCaseStepId() {
        return apiTestCaseStepId;
    }

    public void setApiTestCaseStepId(int apiTestCaseStepId) {
        this.apiTestCaseStepId = apiTestCaseStepId;
    }

    public List<Header> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(List<Header> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public List<Params> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(List<Params> requestParams) {
        this.requestParams = requestParams;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getResultBody() {
        return resultBody;
    }

    public void setResultBody(String resultBody) {
        this.resultBody = resultBody;
    }

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public int getResultTime() {
        return resultTime;
    }

    public void setResultTime(int resultTime) {
        this.resultTime = resultTime;
    }

    public List<ResultAssert> getResultAssert() {
        return resultAssert;
    }

    public void setResultAssert(List<ResultAssert> resultAssert) {
        this.resultAssert = resultAssert;
    }

    public boolean isResultIsPass() {
        return resultIsPass;
    }

    public void setResultIsPass(boolean resultIsPass) {
        this.resultIsPass = resultIsPass;
    }

    public int getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(int creatTime) {
        this.creatTime = creatTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
