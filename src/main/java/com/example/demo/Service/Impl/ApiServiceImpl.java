package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Model.Api;
import com.example.demo.Service.ApiService;
import com.example.demo.units.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Resource
    ApiMapper apiMapper;

    @Override
    public int insertApi(Api api){
        api.setCreatTime((int)(System.currentTimeMillis()/1000));
        api.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return apiMapper.insertApi(api);
    }
    @Override
    public List<Api> findAllByName(String apiName){
        return apiMapper.findAllByName(apiName);
    }

    @Override
    public List<Api> findBySuiteId(Integer apiSuiteId){
        return apiMapper.findAllByApiSuiteId(apiSuiteId);
    }

    @Override
    public Api findById(int apiId){
        return apiMapper.findById(apiId);
    }

    @Override
    public PageInfoNew<Api> findAllWithPage(int pageNum, int pageSize,Integer apsuiteId){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(apiMapper.findAllByApiSuiteId(apsuiteId));
    }

    @Override
    public int updateApi(Api api){
        return apiMapper.updateApi(api);
    }
}
