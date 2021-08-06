package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Mapper.ApiSuiteMapper;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiModel.Header;
import com.example.demo.Model.ApiModel.Params;
import com.example.demo.Model.ApiSuite;
import com.example.demo.Service.FileService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    ApiSuiteMapper apiSuiteMapper;
    @Resource
    ApiMapper apiMapper;

    @Override
    public String AnalysisJson(String fileName, int projectId, String url) {
        if (!StringUtils.isEmpty(FileUtils.readJsonFile(fileName))) {
            setApiSuite(fileName, projectId);
            List<Api> getApiList = getApiList(fileName, projectId);
            for (Api api : getApiList) {
                if (apiMapper.findAllByName(api.getName()).size() < 1) {
                    api.setDomain(url);
//                api.setEnvId();
                    api.setCreateTime(DateToStamp.getTimeStap());
                    api.setUpdateTime(DateToStamp.getTimeStap());
                    apiMapper.insertApi(api);
                } else {
                    log.info("接口已存在，不再导入，接口内容:{}", api);
                }
            }
            return "导入成功";
        } else {
            return "文件不存在";
        }

    }

    /**
     * 创建接口组别
     */
    public void setApiSuite(String fileName, int projectId) {
        JSONObject jsonObject = JSON.parseObject(FileUtils.readJsonFile(fileName));
        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        for (Object ob : jsonArray) {
            ApiSuite apiSuite = new ApiSuite();
            JSONObject js1 = JSON.parseObject(ob.toString());
            String result = js1.get("name").toString();
            int row = apiSuiteMapper.findAllByBP(result, projectId).size();
            if (row < 1) {
                apiSuite.setName(result);
                apiSuite.setRemark(js1.get("description").toString());
                apiSuite.setProjectId(projectId);
                apiSuite.setCreateTime(DateToStamp.getTimeStap());
                apiSuite.setUpdateTime(DateToStamp.getTimeStap());
                apiSuiteMapper.insertApiSuite(apiSuite);
            }
        }
    }

    /**
     * 解析swagger-ui json格式
     * 获取Api集合
     */
    public List<Api> getApiList(String fileName, int projectId) {
        JSONObject js = JSON.parseObject(FileUtils.readJsonFile(fileName));
        String paths = js.get("paths").toString();
        HashMap map = JSON.parseObject(paths, HashMap.class);
        List<Api> getApiList = new ArrayList<>();
        for (Object URL : map.keySet()) {
            Api api = new Api();
            //
            api.setPath(URL.toString());
            api.setProjectId(projectId);
            String methods = map.get(URL).toString();
            HashMap map1 = JSON.parseObject(methods, HashMap.class);
            for (Object key1 : map1.keySet()) {
                JSONObject jsonObject1 = (JSONObject) map1.get(key1);
                api.setMethod(key1.toString());
                api.setName(jsonObject1.get("summary").toString());
                String tags = jsonObject1.get("tags").toString();
                tags = tags.substring(2, tags.length() - 2);
                //判断是否存在suiteName 塞入ApiSuiteId
                List<ApiSuite> apiSuiteList = apiSuiteMapper.findAllByBP(tags, projectId);
                if (apiSuiteList.size() < 1) {
                    ApiSuite apiSuite = new ApiSuite();
                    apiSuite.setName(tags);
                    apiSuite.setProjectId(projectId);
                    apiSuite.setCreateTime(DateToStamp.getTimeStap());
                    apiSuite.setUpdateTime(DateToStamp.getTimeStap());
                    apiSuiteMapper.insertApiSuite(apiSuite);
                    api.setApiSuiteId(apiSuite.getId());
                } else {
                    api.setApiSuiteId(apiSuiteList.get(0).getId());
                }
                //判断json中是否存在parameters
                if (jsonObject1.containsKey("parameters")) {
                    JSONArray array = JSON.parseArray(jsonObject1.get("parameters").toString());
                    JSONObject parameter = JSON.parseObject(array.get(0).toString());
                    //判断json-parameters 中是否存在 in 来获取requestType
                    if (parameter.containsKey("in")) {
                        String requestType = parameter.get("in").toString();
                        if (requestType.equals("body")) {
                            //json格式消息头塞入Content-Type
                            List<Header> headerList = new ArrayList<>();
                            Header header = new Header();
                            header.setKey("Content-Type");
                            header.setValue("application/json");
                            headerList.add(header);
                            api.setRequestHeader(headerList);
                            api.setRequestParamType("raw");
                            JSONObject schema = (JSONObject) parameter.get("schema");
                            //判断是否直接存在key：originalRef
                            if (schema.containsKey("originalRef")) {
                                String parameters = getParams(getDefinitions(js), schema.get("originalRef").toString());
                                api.setRequestBody(parameters);
                            } else {
                                //不是直接的key:originalRef  这外面包了一层key:items
                                JSONObject items = (JSONObject) schema.get("items");
                                String parameters = getParams(getDefinitions(js), items.get("originalRef").toString());
                                api.setRequestBody(parameters);
                            }
                        } else {
                            //塞入入参格式form
                            api.setRequestParamType("form");
                            List<Params> paramsList = new ArrayList<>();
                            for (Object ob : array) {
                                Params params = new Params();
                                params.setKey(JSON.parseObject(ob.toString()).get("name").toString());
                                params.setValue("");
                                paramsList.add(params);
                            }
                            if (key1.toString().equalsIgnoreCase("get")) {
                                api.setRequestParams(paramsList);
                            } else {
                                //非get请求且非json格式消息头塞入Content-Type
                                List<Header> headerList = new ArrayList<>();
                                Header header = new Header();
                                header.setKey("Content-Type");
                                header.setValue("application/x-www-form-urlencoded");
                                headerList.add(header);
                                api.setRequestHeader(headerList);
                                api.setRequestDataParams(paramsList);
                            }
                        }
                    } else {
                        List<Params> paramsList = new ArrayList<>();
                        for (Object ob : array) {
                            Params params = new Params();
                            params.setKey(JSON.parseObject(ob.toString()).get("name").toString());
                            paramsList.add(params);
                        }
                        api.setRequestParams(paramsList);
                    }

                } else {
                    log.info("parameters:null");
                }
            }
            getApiList.add(api);
        }
        return getApiList;
    }

    /**
     * 获取参数集合
     */
    public static JSONObject getDefinitions(JSONObject jsonObject) {
        return (JSONObject) jsonObject.get("definitions");
    }

    /**
     * 获取body参数集合
     */
    public static String getParams(JSONObject jsonObject, String str) {
        JSONObject jsonObject1 = (JSONObject) jsonObject.get(str);
        String js = jsonObject1.get("properties").toString();
        HashMap map = JSON.parseObject(js, HashMap.class);
        Map<String, Object> map1 = new HashMap<>();
        for (Object key : map.keySet()) {
            map1.put(key.toString(), "");
        }
        JSONObject result = new JSONObject(map1);

        return result.toJSONString();
    }

}
