package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Mapper.ApiRequestResultMapper;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Service.ApiRequestResultService;
import com.example.demo.utils.RestAssuredUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiRequestResultServiceImpl implements ApiRequestResultService {

    @Resource
    private ApiRequestResultMapper apiRequestResultMapper;

    @Resource
    private ApiMapper apiMapper;

    private RestAssuredUtil restAssuredUtil;

    @Override
    public ApiRequestResult insertApiRequestResult(int apiId){
        Api api =apiMapper.findById(apiId);
        restAssuredUtil =new RestAssuredUtil(api);
        ApiRequestResult apiRequestResult = restAssuredUtil.requestTestRun();
        apiRequestResultMapper.insertApiRequestResult(apiRequestResult);
        return apiRequestResult ;
    }
}
