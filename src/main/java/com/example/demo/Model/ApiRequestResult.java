package com.example.demo.Model;

import com.example.demo.Model.ApiModel.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class ApiRequestResult implements Serializable {
    private int id;
    @ApiModelProperty(value = "接口id")
    private int apiId;
    @ApiModelProperty(value = "接口名称")
    private String apiName;
    @ApiModelProperty(value = "测试用例id")
    private int apiTestCaseId;
    @ApiModelProperty(value = "测试步骤id")
    private int apiTestCaseStepId;
    @ApiModelProperty(value = "请求头部信息")
    private List<Header> requestHeader;
    @ApiModelProperty(value = "请求入参")
    private List<Params> requestParams;
    @ApiModelProperty(value = "入参类型")
    private String requestParamType;
    @ApiModelProperty(value = "post请求body入参")
    private String requestBody;
    @ApiModelProperty(value = "post请求form-data请求入参")
    private List<Params> requestDataParams;
    @ApiModelProperty(value = "请求方式")
    private String Method;
    @ApiModelProperty(value = "请求路径")
    private String URL;
    @ApiModelProperty(value = "返回结果")
    private String resultBody;
    @ApiModelProperty(value = "返回结果头部信息")
    private List<Header> responseHeaders;
    @ApiModelProperty(value = "结果状态")
    private int resultStatus;
    @ApiModelProperty(value = "接口运行时间")
    private int resultTime;
    @ApiModelProperty(value = "返回结果断言")
    private List<ResultAssert> resultAssert;
    @ApiModelProperty(value = "返回结果断言结果")
    private boolean resultIsPass;
    @ApiModelProperty(value = "返回提取参数结果")
    private List<GetExtractions> resultExtractions;
    @ApiModelProperty(value = "报错信息")
    private String exceptionBody;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;
    private static final long serialVersionUID = 1L;

}
