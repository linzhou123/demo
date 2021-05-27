package com.example.demo.Model;

import com.example.demo.Model.ApiModel.Header;
import com.example.demo.Model.ApiModel.ParameterExtraction;
import com.example.demo.Model.ApiModel.Params;
import com.example.demo.Model.ApiModel.RequestAssert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Api implements Serializable {
    @ApiModelProperty(value = "apiId")
    private int id;
    @ApiModelProperty(value = "环境变量id")
    private int envId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "项目id")
    private int projectId;
    @ApiModelProperty(value = "api类别id")
    private int apiSuiteId;
    @ApiModelProperty(value = "域名")
    private String domain;
    @ApiModelProperty(value = "入参类型")
    private String requestParamType;
    @ApiModelProperty(value = "请求头")
    private List<Header> requestHeader;
    @ApiModelProperty(value = "请求入参")
    private List<Params> requestParams;
    @ApiModelProperty(value = "post请求form-data请求入参")
    private List<Params> requestDataParams;
    @ApiModelProperty(value = "post请求body入参")
    private String requestBody;
    @ApiModelProperty(value = "请求方式")
    private String method;
    @ApiModelProperty(value = "请求路径")
    private String path;
    @ApiModelProperty(value = "断言校验")
    private List<RequestAssert> requestAssert;
    @ApiModelProperty(value = "提取参数内容")
    List<ParameterExtraction> parameterExtractions;
    @ApiModelProperty(value = "创建时间")
    private int createTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString(){
        return "[id="+id+",name="+name+",projectId="+projectId+",apiSuiteId="+apiSuiteId+",domain="+domain+
                ",requestParamType" +requestParamType+",requestHeader="+requestHeader+",requestParams="
                +requestParams+",requestBody="+requestBody+",method="+method+",path="+path+",requestAssert="
                +requestAssert+",createTime="+createTime+",updateTime"+updateTime+"]";
    }
}
