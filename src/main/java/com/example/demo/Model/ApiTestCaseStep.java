package com.example.demo.Model;

import com.example.demo.Model.ApiModel.Header;
import com.example.demo.Model.ApiModel.Params;
import com.example.demo.Model.ApiModel.RequestAssert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
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
    private int createTime;
    @ApiModelProperty(value = "测试用例更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;

}
