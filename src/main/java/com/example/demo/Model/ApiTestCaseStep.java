package com.example.demo.Model;

import com.example.demo.Model.ApiModel.Header;
import com.example.demo.Model.ApiModel.Params;
import com.example.demo.Model.ApiModel.RequestAssert;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class ApiTestCaseStep implements Serializable {
    private int id;
    @ApiModelProperty(value = "步骤名称")
    private String name;
    @ApiModelProperty(value = "接口Id")
    private int apiId;
    @ApiModelProperty(value = "测试用例id")
    private int testCaseId;
    @ApiModelProperty(value = "域名")
    private String domain;
    @ApiModelProperty(value = "请求入参类型")
    private String requestParamType;
    @ApiModelProperty(value = "请求消息头")
    private List<Header> requestHeader;
    @ApiModelProperty(value = "请求入参")
    private List<Params> requestParams;
    @ApiModelProperty(value = "post请求非raw请求入参")
    private List<Params> requestDataParams;
    @ApiModelProperty(value = "post请求raw请求内容")
    private String requestBody;
    @ApiModelProperty(value = "请求方式")
    private String method;
    @ApiModelProperty(value = "请求路径")
    private String path;
    @ApiModelProperty(value = "请求断言")
    private List<RequestAssert> requestAssert;
    @ApiModelProperty(value = "步骤顺序下标")
    private int sort;
    @ApiModelProperty(value = "测试用例创建时间")
    private int creatTime;
    @ApiModelProperty(value = "测试用例更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRequestParamType() {
        return requestParamType;
    }

    public void setRequestParamType(String requestParamType) {
        this.requestParamType = requestParamType;
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

    public List<Params> getRequestDataParams() {
        return requestDataParams;
    }

    public void setRequestDataParams(List<Params> requestDataParams) {
        this.requestDataParams = requestDataParams;
    }

    public void setRequestParams(List<Params> requestParams) {
        this.requestParams = requestParams;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<RequestAssert> getRequestAssert() {
        return requestAssert;
    }

    public void setRequestAssert(List<RequestAssert> requestAssert) {
        this.requestAssert = requestAssert;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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
