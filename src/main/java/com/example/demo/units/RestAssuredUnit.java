package com.example.demo.units;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.Header;
import com.example.demo.Model.Params;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class RestAssuredUnit {
    private Response response=null;
    private Api api;
    public ApiRequestResult apiRequestResult;
    public RestAssuredUnit(Api api ,ApiRequestResult apiRequestResult){
        this.api=api;
        this.apiRequestResult=new ApiRequestResult();
    }
    public ApiRequestResult requestTestRun(){
        //拼接URL传入
        String URL=api.getDomain()+api.getPath();
        switch (api.getMethod()){
            case "Post":
                response= given().headers(getHeaders()).params(getParams()).when().post(URL);
                break;
            case "Get":
                response=given().headers(getHeaders()).params(getParams()).when().get(URL);
                break;
            default:
                return null;
        }
        apiRequestResult.setApiId(api.getId());
        apiRequestResult.setApiName(api.getName());
        apiRequestResult.setRequestHeader(api.getRequestHeader());
        apiRequestResult.setRequestParams(api.getRequestParams());
        apiRequestResult.setMethod(api.getMethod());
        apiRequestResult.setURL(URL);
        apiRequestResult.setResultBody(response.getBody().prettyPrint());
        apiRequestResult.setResultStatus(response.getStatusCode());
        apiRequestResult.setResultIsPass(false);
        apiRequestResult.setResultTime((int)response.getTime());
        apiRequestResult.setCreatTime((int)(System.currentTimeMillis()/1000));
        apiRequestResult.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return apiRequestResult;

    }
    public Map<String,Object> getHeaders(){
        List<Header> headerList= JSONObject.parseArray(api.getRequestHeader().toString(),Header.class);
        Map<String,Object> getHeaders=new HashMap<String ,Object>();
        try {
            if (headerList.size()>0){
                for (Header header : headerList){
                    getHeaders.put(header.getKey(),header.getValue());
                }
                return getHeaders;
            }
            else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return getHeaders;
    }
    public Map<String,Object> getParams(){
        List<Params> paramsList=JSONObject.parseArray(api.getRequestParams().toString(), Params.class);
        Map<String,Object> getParams=new HashMap<String ,Object>();
        try {
            if (paramsList.size()>0){
                for (Params params :paramsList){
                    getParams.put(params.getKey(),params.getValue());
                }
                return getParams;
            }
            else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return getParams;
    }
}
