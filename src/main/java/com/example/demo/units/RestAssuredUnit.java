package com.example.demo.units;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiModel.Header;
import com.example.demo.Model.ApiModel.Params;
import com.example.demo.Model.ApiModel.RequestAssert;
import com.example.demo.Model.ApiModel.ResultAssert;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.ApiTestCaseStep;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;

public class RestAssuredUnit {
    private boolean flag = false;
    private Response response = null;
    private Api api;
    public ApiRequestResult apiRequestResult;
    public ApiTestCaseStep apiTestCaseStep;
    public RestAssuredUnit(Api api) {
        this.api = api;
        this.apiRequestResult = new ApiRequestResult();
    }
    public RestAssuredUnit(ApiTestCaseStep apiTestCaseStep){
        this.apiTestCaseStep =apiTestCaseStep;
        this.apiRequestResult =new ApiRequestResult();
    }
    //debug api
    public ApiRequestResult requestTestRun() {
        //拼接URL传入
        String URL = api.getDomain() + api.getPath();
        switch (api.getMethod()) {
            case "Post":
                if (api.getRequestParamType().equals("raw")) {
                    response = given().headers(getHeaders(api.getRequestHeader())).body(api.getRequestBody()).post(URL);
                } else {
                    response = given().headers(getHeaders(api.getRequestHeader())).params(getParams(api.getRequestParams())).when().post(URL);
                }
                break;
            case "Get":
                response = given().headers(getHeaders(api.getRequestHeader())).params(getParams(api.getRequestParams())).when().get(URL);
                break;
            case "Delete":
                response = given().headers(getHeaders(api.getRequestHeader())).params(getParams(api.getRequestParams())).when().delete(URL);
                break;
            case "Put":
                response = given().headers(getHeaders(api.getRequestHeader())).params(getParams(api.getRequestParams())).when().put(URL);
                break;
            default:
                return null;
        }
        //result塞入接口运行结果
        apiRequestResult.setApiId(api.getId());
        apiRequestResult.setApiName(api.getName());
        apiRequestResult.setRequestHeader(api.getRequestHeader());
        apiRequestResult.setRequestParams(api.getRequestParams());
        apiRequestResult.setMethod(api.getMethod());
        apiRequestResult.setURL(URL);
        apiRequestResult.setResultBody(response.getBody().prettyPrint());
        apiRequestResult.setResultStatus(response.getStatusCode());
        apiRequestResult.setResultAssert(getResultAssert(api.getRequestAssert()));
        apiRequestResult.setResultIsPass(requestAssert());
        apiRequestResult.setResultTime((int) response.getTime());
        apiRequestResult.setCreatTime((int) (System.currentTimeMillis() / 1000));
        apiRequestResult.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return apiRequestResult;

    }

    //执行单条用例步骤
    public ApiRequestResult requestCaseRun(){
        //拼接URL传入
        String URL = apiTestCaseStep.getDomain() + apiTestCaseStep.getPath();
        switch (apiTestCaseStep.getMethod()) {
            case "Post":
                if (apiTestCaseStep.getRequestParamType().equals("raw")) {
                    response = given().headers(getHeaders(apiTestCaseStep.getRequestHeader())).body(apiTestCaseStep.getRequestBody()).post(URL);
                } else {
                    response = given().headers(getHeaders(apiTestCaseStep.getRequestHeader())).params(getParams(apiTestCaseStep.getRequestParams())).when().post(URL);
                }
                break;
            case "Get":
                response = given().headers(getHeaders(apiTestCaseStep.getRequestHeader())).params(getParams(apiTestCaseStep.getRequestParams())).when().get(URL);
                break;
            case "Delete":
                response = given().headers(getHeaders(apiTestCaseStep.getRequestHeader())).params(getParams(apiTestCaseStep.getRequestParams())).when().delete(URL);
                break;
            case "Put":
                response = given().headers(getHeaders(apiTestCaseStep.getRequestHeader())).params(getParams(apiTestCaseStep.getRequestParams())).when().put(URL);
                break;
            default:
                return null;
        }
        //result塞入接口运行结果
        apiRequestResult.setApiId(apiTestCaseStep.getApiId());
        apiRequestResult.setApiName(apiTestCaseStep.getName());
        apiRequestResult.setApiTestCaseStepId(apiTestCaseStep.getId());
        apiRequestResult.setApiTestCaseId(apiTestCaseStep.getTestCaseId());
        apiRequestResult.setRequestHeader(apiTestCaseStep.getRequestHeader());
        apiRequestResult.setRequestParams(apiTestCaseStep.getRequestParams());
        apiRequestResult.setMethod(apiTestCaseStep.getMethod());
        apiRequestResult.setURL(URL);
        apiRequestResult.setResultBody(response.getBody().prettyPrint());
        apiRequestResult.setResultStatus(response.getStatusCode());
        apiRequestResult.setResultAssert(getResultAssert(apiTestCaseStep.getRequestAssert()));
        apiRequestResult.setResultIsPass(requestAssert());
        apiRequestResult.setResultTime((int) response.getTime());
        apiRequestResult.setCreatTime((int) (System.currentTimeMillis() / 1000));
        apiRequestResult.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return apiRequestResult;
    }


    /**
     * 获取消息头
     * */
    public Map<String, Object> getHeaders(List<Header> hList) {
        if(Objects.isNull(hList)){
            return Collections.EMPTY_MAP;
        }
        List<Header> headerList = JSONObject.parseArray(hList.toString(), Header.class);
        Map<String, Object> getHeaders = new HashMap<String, Object>();
        try {
            if (headerList.size() > 0) {
                for (Header header : headerList) {
                    getHeaders.put(header.getKey(), header.getValue());
                }
                return getHeaders;
            } else {
                return Collections.EMPTY_MAP;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getHeaders;
    }

    /**
     * 获取参数
     * */
    public Map<String, Object> getParams(List<Params> ps) {
        //json 转换List<Params>格式
        if (Objects.isNull(ps)){
            return Collections.EMPTY_MAP;
        }
        List<Params> paramsList = JSONObject.parseArray(ps.toString(), Params.class);
        Map<String, Object> getParams = new HashMap<String, Object>();
        try {
            if (paramsList.size() > 0) {
                for (Params params : paramsList) {
                    getParams.put(params.getKey(), params.getValue());
                }
                return getParams;
            } else {
                return Collections.EMPTY_MAP;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getParams;
    }

    /**
     * 返回断言结果集：ResultAssert
     * */
    public List<ResultAssert> getResultAssert(List<RequestAssert> rAssert){
        if(Objects.isNull(rAssert)){
            return Collections.EMPTY_LIST;
        }
        List<ResultAssert> resultAssertList =new ArrayList<ResultAssert>();
            List<RequestAssert> assertList = JSONObject.parseArray(rAssert.toString(), RequestAssert.class);
            for (RequestAssert requestAssert:assertList){
                ResultAssert resultAssert=new ResultAssert();
                resultAssert.setCheckList(requestAssert.getCheckList());
                resultAssert.setValue(requestAssert.getValue());
                if (JsonPath.parse(apiRequestResult.getResultBody()).read("$."+requestAssert.getCheckList()).toString().equals(requestAssert.getValue())){
                    resultAssert.setResult(true);
                }else {
                    resultAssert.setResult(false);
                }
                resultAssertList.add(resultAssert);
            }
        return resultAssertList;
    }

    /**
     * 判断断言返回结果集中是否有断言失败结果，有则返回false
     * */
    public boolean requestAssert() {
        List<ResultAssert> resultAssertList =apiRequestResult.getResultAssert();
        if (resultAssertList.size() > 0) {
            for (ResultAssert resultAssert : resultAssertList) {
                if (!resultAssert.isResult()){
                    flag=false;
                    return flag;
                }else {
                    flag=true;
                }
            }
        } else {
            flag = true;
            return flag;
        }
        return flag;
    }
}
