package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Mapper.ApiRequestResultMapper;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Service.ApiRequestResultService;
import com.example.demo.units.RestAssuredUnit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiRequestResultServiceImpl implements ApiRequestResultService {

    @Resource
    private ApiRequestResultMapper apiRequestResultMapper;

    @Resource
    private ApiMapper apiMapper;

    private RestAssuredUnit restAssuredUnit;

    private ApiRequestResult apiRequestResult;

    @Override
    public int insertApiRequestResult(int apiId){
        Api api =apiMapper.findById(apiId);
        restAssuredUnit=new RestAssuredUnit(api ,apiRequestResult);
        return apiRequestResultMapper.insertApiRequestResult(restAssuredUnit.requestTestRun());
    }
}
