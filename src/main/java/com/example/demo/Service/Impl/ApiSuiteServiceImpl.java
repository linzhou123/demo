package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Mapper.ApiSuiteMapper;
import com.example.demo.Model.ApiSuite;
import com.example.demo.Service.ApiSuiteService;
import com.example.demo.units.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiSuiteServiceImpl implements ApiSuiteService {
    @Resource
    private ApiSuiteMapper apiSuiteMapper;

    @Override
    public int insertApiSuite(ApiSuite apiSuite){
        apiSuite.setCreatTime((int)(System.currentTimeMillis()/1000));
        apiSuite.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return apiSuiteMapper.insertApiSuite(apiSuite);
    }

    @Override
    public int updateApiSuite(ApiSuite apiSuite){
        apiSuite.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return apiSuiteMapper.updateApiSuite(apiSuite);
    }

    @Override
    public int deleteById(int apiSuiteId){
        return apiSuiteMapper.deleteById(apiSuiteId);
    }

    @Override
    public List<ApiSuite> findAllByName(String apiSuiteName){
        return  apiSuiteMapper.findAllByName(apiSuiteName);
    }

    @Override
    public ApiSuite findAllById(int apiSuiteId){
        return  apiSuiteMapper.findAllById(apiSuiteId);
    }

    @Override
    public List<ApiSuite> findAll(){
        return apiSuiteMapper.findAll();
    }

    @Override
    public PageInfoNew<ApiSuite> findAllWithPage(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(apiSuiteMapper.findAll());
    }

    @Override
    public JSONObject js(int apiSuiteId){
        ApiSuite apiSuites = apiSuiteMapper.findAllById(apiSuiteId);
        return JSONObject.parseObject(JSON.toJSONString(apiSuites));
    }
}
