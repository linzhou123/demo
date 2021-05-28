package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Mapper.EnvParamsMapper;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiModel.*;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.ApiTestCaseStep;
import com.example.demo.Model.EnvParams;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static io.restassured.RestAssured.given;

@Slf4j
public class RestAssuredUtil {
    private boolean flag = false;
    private Response response = null;
    private Api api;
    public ApiRequestResult apiRequestResult;
    public ApiTestCaseStep apiTestCaseStep;
    private RequestSpecification requestSpecification;
    @Autowired
    private EnvParamsMapper envParamsMapper;

    public RestAssuredUtil(Api api) {
        this.api = api;
        this.apiRequestResult = new ApiRequestResult();
        requestSpecification = given();
    }

    public RestAssuredUtil(ApiTestCaseStep apiTestCaseStep) {
        this.apiTestCaseStep = apiTestCaseStep;
        this.apiRequestResult = new ApiRequestResult();
        requestSpecification = given();
    }

    //
    public void applyHeaders() {
        requestSpecification.headers(getHeaders(api.getRequestHeader()));
    }

    //debug api
    public ApiRequestResult requestTestRun() {
        //拼接URL传入
        log.info("---------开始执行自动化接口用例---------");
        String URL = api.getDomain().concat(api.getPath());
        if (api.getEnvId() != 0) {
            URL = getStringByEnv(URL, api.getEnvId());
        }
        apiRequestResult.setApiId(api.getId());
        apiRequestResult.setApiName(api.getName());
        apiRequestResult.setRequestHeader(api.getRequestHeader());
        apiRequestResult.setRequestParams(api.getRequestParams());
        apiRequestResult.setRequestParamType(api.getRequestParamType());
        apiRequestResult.setRequestBody(api.getRequestBody());
        apiRequestResult.setRequestDataParams(api.getRequestDataParams());
        apiRequestResult.setMethod(api.getMethod());
        apiRequestResult.setURL(URL);
        try {
            switch (api.getMethod()) {
                case "Post":
                    if (api.getRequestParamType().equals("raw")) {
                        response = given().headers(getHeaders(api.getRequestHeader())).body(api.getRequestBody()).post(URL);
                    } else {
                        response = given().headers(getHeaders(api.getRequestHeader())).params(getParams(api.getRequestDataParams())).when().post(URL);
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
                    response = given().headers(getHeaders(api.getRequestHeader())).params(getParams(api.getRequestParams())).when().get(URL);
                    break;
            }
        } catch (Exception e) {
            apiRequestResult.setCreateTime(DateToStamp.getTimeStap());
            apiRequestResult.setUpdateTime(DateToStamp.getTimeStap());
            apiRequestResult.setExceptionBody(e.getMessage());
            log.error("运行自动化接口报错:", e);
            return apiRequestResult;
        }

        //result塞入接口运行结果
        apiRequestResult.setExceptionBody("无异常");
        apiRequestResult.setResultBody(response.getBody().prettyPrint());
        apiRequestResult.setResponseHeaders(getRspHeaders(response));
        apiRequestResult.setResultStatus(response.getStatusCode());
        apiRequestResult.setResultAssert(getResultAssert(api.getRequestAssert()));
        apiRequestResult.setResultIsPass(requestAssert());
        apiRequestResult.setResultTime((int) response.getTime());
        apiRequestResult.setCreateTime(DateToStamp.getTimeStap());
        apiRequestResult.setUpdateTime(DateToStamp.getTimeStap());
        log.info("获取接口自动化用例结果:" + apiRequestResult.toString());
        log.info("---------接口自动化用例运行完成---------");
        return apiRequestResult;

    }

    //执行单条用例步骤
    public ApiRequestResult requestCaseRun() {
        //拼接URL传入
        log.info("---------开始执行自动化接口用例---------");
        log.info("获取接口测试用例参数:" + JSON.toJSONString(apiTestCaseStep));
        String URL = apiTestCaseStep.getDomain().concat(apiTestCaseStep.getPath());
        //判断请求方式 get、pst、delete、put
        apiRequestResult.setApiId(apiTestCaseStep.getApiId());
        apiRequestResult.setApiName(apiTestCaseStep.getName());
        apiRequestResult.setApiTestCaseStepId(apiTestCaseStep.getId());
        apiRequestResult.setApiTestCaseId(apiTestCaseStep.getTestCaseId());
        apiRequestResult.setRequestHeader(apiTestCaseStep.getRequestHeader());
        apiRequestResult.setRequestParams(apiTestCaseStep.getRequestParams());
        apiRequestResult.setRequestParamType(apiTestCaseStep.getRequestParamType());
        apiRequestResult.setRequestBody(apiTestCaseStep.getRequestBody());
        apiRequestResult.setRequestDataParams(apiTestCaseStep.getRequestDataParams());
        apiRequestResult.setMethod(apiTestCaseStep.getMethod());
        apiRequestResult.setURL(URL);
        try {

            switch (apiTestCaseStep.getMethod()) {
                case "Post":
                    if (apiTestCaseStep.getRequestParamType().equals("raw")) {
                        response = given().headers(getHeaders(apiTestCaseStep.getRequestHeader())).body(apiTestCaseStep.getRequestBody()).when().post(URL);
                    } else {
                        response = given().headers(getHeaders(apiTestCaseStep.getRequestHeader())).params(getParams(apiTestCaseStep.getRequestDataParams())).when().post(URL);
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
        } catch (Exception e) {
            log.error("运行自动化接口报错:", e);
            apiRequestResult.setExceptionBody(e.getMessage());
            apiRequestResult.setCreateTime(DateToStamp.getTimeStap());
            apiRequestResult.setUpdateTime(DateToStamp.getTimeStap());
            return apiRequestResult;
        }
        Headers allHeaders = response.getHeaders();
        //result塞入接口运行结果
        apiRequestResult.setResultBody(response.getBody().prettyPrint());
        apiRequestResult.setResponseHeaders(getRspHeaders(response));
        apiRequestResult.setResultStatus(response.getStatusCode());
        apiRequestResult.setResultAssert(getResultAssert(apiTestCaseStep.getRequestAssert()));
        apiRequestResult.setResultIsPass(requestAssert());
        apiRequestResult.setExceptionBody("无异常");
        apiRequestResult.setResultTime((int) response.getTime());
        apiRequestResult.setCreateTime(DateToStamp.getTimeStap());
        apiRequestResult.setUpdateTime(DateToStamp.getTimeStap());
        log.info("---------接口自动化用例运行完成---------");
        return apiRequestResult;
    }


    /**
     * 获取消息头
     */
    public Map<String, Object> getHeaders(List<Header> hList) {
        if (Objects.isNull(hList)) {
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
            log.error("获取消息头报错：", e);
        }

        return getHeaders;
    }

    /**
     * 获取返回结果头部信息以list形式
     */
    public List<Header> getRspHeaders(Response response) {
        List<Header> headerList = new ArrayList<>();
        response.getHeaders().asList().forEach(x -> {
            Header header = new Header();
            header.setKey(x.getName());
            header.setValue(x.getValue());
            headerList.add(header);
        });
        return headerList;
    }

    /**
     * 获取参数
     */
    public Map<String, Object> getParams(List<Params> ps) {
        //json 转换List<Params>格式
        if (Objects.isNull(ps)) {
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
            log.error("获取参数报错：", e);
        }

        return getParams;
    }

    /**
     * 返回断言结果集：ResultAssert
     */
    public List<ResultAssert> getResultAssert(List<RequestAssert> rAssert) {
        if (Objects.isNull(rAssert)) {
            return Collections.EMPTY_LIST;
        }
        List<ResultAssert> resultAssertList = new ArrayList<ResultAssert>();
        List<RequestAssert> assertList = JSONObject.parseArray(rAssert.toString(), RequestAssert.class);
        for (RequestAssert requestAssert : assertList) {
            ResultAssert resultAssert = new ResultAssert();
            resultAssert.setCheckList(requestAssert.getCheckList());
            resultAssert.setValue(requestAssert.getValue());
            try {
                String realValue = JsonPath.parse(apiRequestResult.getResultBody()).read("$." + requestAssert.getCheckList()).toString();
                resultAssert.setRealValue(realValue);
                if (realValue.equals(requestAssert.getValue())) {
                    resultAssert.setResult(true);
                } else {
                    resultAssert.setResult(false);
                }
                resultAssertList.add(resultAssert);
            } catch (PathNotFoundException e) {
                log.error("解析失败，无该元素：" + requestAssert.getCheckList());
                resultAssert.setResult(false);
                resultAssertList.add(resultAssert);
                System.out.println(requestAssert.getCheckList());
            }

        }
        return resultAssertList;
    }

    /**
     * 判断断言返回结果集中是否有断言失败结果，有则返回false
     */
    public boolean requestAssert() {
        List<ResultAssert> resultAssertList = apiRequestResult.getResultAssert();
        if (resultAssertList.size() > 0) {
            for (ResultAssert resultAssert : resultAssertList) {
                if (!resultAssert.isResult()) {
                    flag = false;
                    return flag;
                } else {
                    flag = true;
                }
            }
        } else {
            flag = true;
            return flag;
        }
        return flag;
    }

    /**
     * 判断字符串是否存在标识@
     */
    public String getStringByEnv(String str, int envId) {
        if (str.indexOf("@") != -1) {
            int start = str.indexOf("{") + 1;
            int end = str.indexOf("}");
            String s = str.substring(start, end);
            String oldString = "@{" + s + "}";
            log.info("截取开始字符串位置:" + start + ",截取结束字符串位置:" + end + ",获取截取字符串:" + s);
            EnvParams envParams = new EnvParams();
            envParams = envParamsMapper.selectByName(s);
            log.info(JSON.toJSONString(envParams));
            if (!envParams.getName().isEmpty()) {
                for (EnvGParams envGParams : envParams.getValue()) {
                    if (envId == envGParams.getEnvId()) {
                        str.replace(oldString, envGParams.getEnvValue());
                        log.info("获取修改后字符串:" + str);
                    }
                }
            }
        } else {
            return str;
        }
        return str;
    }
}
