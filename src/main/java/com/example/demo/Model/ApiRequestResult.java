package com.example.demo.Model;

import com.example.demo.Model.ApiModel.Header;
import com.example.demo.Model.ApiModel.Params;
import com.example.demo.Model.ApiModel.ResultAssert;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class ApiRequestResult implements Serializable {
    private int id;
    private int apiId;
    private String apiName;
    private int apiTestCaseId;
    private int apiTestCaseStepId;
    private List<Header> requestHeader;
    private List<Params> requestParams;
    private String requestParamType;
    private String requestBody;
    private List<Params> requestDataParams;
    private String Method;
    private String URL;
    private String resultBody;
    private List<Header> responseHeaders;
    private int resultStatus;
    private int resultTime;
    private List<ResultAssert> resultAssert;
    private boolean resultIsPass;
    private String exceptionBody;
    private int creatTime;
    private int updateTime;
    private static final long serialVersionUID = 1L;

}
