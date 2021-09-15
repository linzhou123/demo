package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Enums.ExtractionTypeEnum;
import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Mapper.EnvParamsMapper;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiModel.*;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.EnvParams;
import com.example.demo.Service.ApiService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Resource
    ApiMapper apiMapper;
    @Resource
    EnvParamsMapper envParamsMapper;

    @Override
    public int insertApi(Api api) {
        if (Objects.isNull(api.getRequestAssert())) {
            api.setRequestAssert(Collections.emptyList());
        }
        api.setCreateTime(DateToStamp.getTimeStap());
        api.setUpdateTime(DateToStamp.getTimeStap());
        log.info("添加api：" + JSON.toJSONString(api));
        return apiMapper.insertApi(api);
    }

    @Override
    public List<Api> findAllByName(String apiName) {
        return apiMapper.findAllByName(apiName);
    }

    @Override
    public List<Api> findBySuiteId(Integer apiSuiteId) {
        return apiMapper.findAllByApiSuiteId(apiSuiteId);
    }

    @Override
    public Api findById(int apiId) {
        return apiMapper.findById(apiId);
    }

    @Override
    public PageInfoNew<Api> findAllWithPage(int pageNum, int pageSize, Integer apiSuiteId, Integer projectId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfoNew<>(apiMapper.findAllToPage(apiSuiteId, projectId));
    }

    @Override
    public int updateApi(Api api) {
        return apiMapper.updateApi(api);
    }

    @Override
    public void deleteApi(int id) {
        apiMapper.deleteApi(id);
    }

    @Override
    public ApiRequestResult requestTestRun(Api api, List<GetExtractions> getExtractionsList) {
        //拼接URL传入
        Response response = null;
        ApiRequestResult apiRequestResult = new ApiRequestResult();
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.headers(getHeaders(api.getRequestHeader(), getExtractionsList));
        if (!api.getMethod().equalsIgnoreCase("get")){
            if (api.getRequestParamType().equals("raw")){
                requestSpecification.body(api.getRequestBody());
            }else{
                requestSpecification.params(getParams(api.getRequestDataParams(),getExtractionsList));
            }
        }else {
            requestSpecification.params(getParams(api.getRequestParams(), getExtractionsList));
        }
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
                    response =requestSpecification.when().post(URL);
                    break;
                case "Get":
                    response =requestSpecification.when().get(URL);
                    break;
                case "Delete":
                    response =requestSpecification.when().delete(URL);
                    break;
                case "Put":
                    response =requestSpecification.when().put(URL);
                    break;
                default:
                    apiRequestResult.setExceptionBody("不支持该请求方式 :" + URL);
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
        apiRequestResult.setResultAssert(getResultAssert(api.getRequestAssert(), apiRequestResult));
        apiRequestResult.setResultIsPass(requestAssert(apiRequestResult));
        apiRequestResult.setResultExtractions(getParameterExtractions(response, api.getParameterExtractions()));
        apiRequestResult.setResultTime((int) response.getTime());
        apiRequestResult.setCreateTime(DateToStamp.getTimeStap());
        apiRequestResult.setUpdateTime(DateToStamp.getTimeStap());
        log.info("获取接口自动化用例结果:" + apiRequestResult.toString());
        log.info("---------接口自动化用例运行完成---------");
        return apiRequestResult;
    }

    /**
     * 获取消息头
     *
     * @param hList              消息头列表
     * @param getExtractionsList
     * @return 以map 格式返回消息头
     */
    public Map<String, Object> getHeaders(List<Header> hList, List<GetExtractions> getExtractionsList) {
        if (Objects.isNull(hList)) {
            return Collections.EMPTY_MAP;
        }
        List<Header> headerList = JSONObject.parseArray(hList.toString(), Header.class);
        Map<String, Object> getHeaders = new HashMap<String, Object>();
        try {
            if (headerList.size() > 0) {
                for (Header header : headerList) {
                    getHeaders.put(header.getKey(), getStringByParameterExtraction(header.getValue(), getExtractionsList));
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
     *
     * @param response 请求结果
     * @return 返回response头部信息
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
     * 获取请求参数
     *
     * @param ps                 参数列表
     * @param getExtractionsList 环境变量值
     * @return 以map格式获取请求参数
     */
    public Map<String, Object> getParams(List<Params> ps, List<GetExtractions> getExtractionsList) {
        //json 转换List<Params>格式
        if (Objects.isNull(ps)) {
            return Collections.EMPTY_MAP;
        }
        List<Params> paramsList = JSONObject.parseArray(ps.toString(), Params.class);
        Map<String, Object> getParams = new HashMap<String, Object>();
        try {
            if (paramsList.size() > 0) {
                for (Params params : paramsList) {
                    getParams.put(params.getKey(), getStringByParameterExtraction(params.getValue(), getExtractionsList));
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
     *
     * @param rAssert          断言列表
     * @param apiRequestResult 接口运行结果
     * @return 返回断言结果集
     */
    public List<ResultAssert> getResultAssert(List<RequestAssert> rAssert, ApiRequestResult apiRequestResult) {
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
//                System.out.println(requestAssert.getCheckList());
            }

        }
        return resultAssertList;
    }

    /**
     * 判断断言返回结果集中是否有断言失败结果，有则返回false
     *
     * @param apiRequestResult 接口运行结果
     * @return 返回结果集中是否有断言失败结果，有则返回false
     */
    public boolean requestAssert(ApiRequestResult apiRequestResult) {
        boolean flag = false;
        List<ResultAssert> resultAssertList = apiRequestResult.getResultAssert();
        if (resultAssertList.size() > 0) {
            for (ResultAssert resultAssert : resultAssertList) {
                if (!resultAssert.isResult()) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断字符串是否存在标识@
     *
     * @param envId 环境id
     * @param str   替换获取的原始值
     * @return 最终获取的值
     */
    public String getStringByEnv(String str, int envId) {
        //判断是否存在@标识
        if (str.indexOf("@") != -1) {
            //正则表达式获取@{}内数据
            Pattern regex = Pattern.compile("@\\{(.*?)\\}");
            Matcher matcher = regex.matcher(str);
            while (matcher.find()) {
                String findString = matcher.group(1);
                String oldString = "@{" + findString + "}";
                log.info("正则表达式获取结果:" + findString);
                EnvParams envParams = envParamsMapper.selectByName(findString);
                if (envParams != null) {
                    List<EnvGParams> envParamsList = JSON.parseArray(JSONObject.toJSONString(envParams.getValue()), EnvGParams.class);
                    for (EnvGParams envGParams : envParamsList) {
                        //判断是否存在环境id且相等
                        if (envId == envGParams.getEnvId()) {
                            String newString = envGParams.getEnvValue();
                            //替换@{string}内容
                            str = str.replace(oldString, newString);
                            log.info(",oldString:" + oldString + ",newString:" + newString + "获取修改后字符串:" + str);
                        }
                    }
                }

            }
//            int start = str.indexOf("{")+1;
//            int end =str.indexOf("}");
//            String s =str.substring(start,end);
//            String oldString ="@{"+s+"}";
//            log.info("截取开始字符串位置:"+start+",截取结束字符串位置:"+end+",获取截取字符串:"+s);
        } else {
            return str;
        }
        return str;
    }

    /**
     * 获取提取参数内容
     *
     * @param response                请求结果
     * @param parameterExtractionList 请求提取参数列表
     * @return 返回提取后的结果
     */
    public List<GetExtractions> getParameterExtractions(Response response, List<ParameterExtraction> parameterExtractionList) {
        if (Objects.isNull(parameterExtractionList)) {
            return Collections.EMPTY_LIST;
        }
        String getParameterExtraction = null;
        List<GetExtractions> getExtractionsList = new ArrayList<>();
        GetExtractions getExtractions;
        parameterExtractionList = JSONObject.parseArray(parameterExtractionList.toString(), ParameterExtraction.class);
        for (ParameterExtraction parameterExtraction : parameterExtractionList) {
            getExtractions = new GetExtractions();
            switch (ExtractionTypeEnum.valueOfType(parameterExtraction.getDataType())) {
                //判断数据源来自返回结果&返回头
                case BodyExtraction:
                    try {
                        //JsonPath 通过表达式获取参数内容
                        getParameterExtraction = JsonPath.parse(response.getBody().prettyPrint()).read("$." + parameterExtraction.getExpression()).toString();
                        getExtractions.setExtractName(parameterExtraction.getExtractName());
                        getExtractions.setValue(getParameterExtraction);
                        getExtractionsList.add(getExtractions);
                    } catch (PathNotFoundException e) {
                        log.error("解析失败，无该元素：" + parameterExtraction.getExpression());
                        getExtractions.setExtractName(parameterExtraction.getExtractName());
                        getExtractions.setValue(getParameterExtraction);
                        getExtractionsList.add(getExtractions);
                    }
                    break;
                case HeardExtraction:
                    //获取返回结果
                    List<Header> headerList = getRspHeaders(response);
                    log.info("根据类型获取返回头内容" + headerList.toString());
                    for (Header header : headerList) {
                        log.info("提取参数：" + JSON.toJSONString(parameterExtraction.getExpression()) +
                                "header；" + JSON.toJSONString(header.getKey()));
                        if (parameterExtraction.getExpression().equals(header.getKey())) {
                            getParameterExtraction = header.getValue();
                            getExtractions.setExtractName(parameterExtraction.getExtractName());
                            getExtractions.setValue(getParameterExtraction);
                            getExtractionsList.add(getExtractions);
                        }
                    }
                    break;
                default:
                    break;

            }
        }
        return getExtractionsList;

    }

    /**
     * 提取参数使用${}标识
     *
     * @param str
     * @param getExtractionsList
     * @return
     */
    public String getStringByParameterExtraction(String str, List<GetExtractions> getExtractionsList) {
        log.info("获取要替换参数列表" + getExtractionsList);
        if (str.indexOf("$") != -1) {
            log.info("-----进入提取接口---");
            Pattern regex = Pattern.compile("\\$\\{(.*?)\\}");
            Matcher matcher = regex.matcher(str);
            while (matcher.find()) {
                String findString = matcher.group(1);
                String oldString = "${" + findString + "}";
                for (GetExtractions getExtractions : getExtractionsList) {
                    if (findString.equals(getExtractions.getExtractName())) {
                        String newString = getExtractions.getValue();
                        str = str.replace(oldString, newString);
                    }
                }
            }
        } else {
            return str;
        }
        log.info("返回修改后字符串" + str);
        return str;
    }


}
