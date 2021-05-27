package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Mapper.ApiRequestResultMapper;
import com.example.demo.Model.Api;
import com.example.demo.Model.ApiModel.GetExtractions;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Service.ApiRequestResultService;
import com.example.demo.Service.ApiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiRequestResultServiceImpl implements ApiRequestResultService {

    @Resource
    private ApiRequestResultMapper apiRequestResultMapper;

    @Resource
    private ApiMapper apiMapper;

    @Resource
    private ApiService apiService;

//    private RestAssuredUtil restAssuredUtil;

    @Override
    public ApiRequestResult insertApiRequestResult(int apiId){
        Api api =apiMapper.findById(apiId);
        List<GetExtractions> getExtractionsList=new ArrayList<>();
        ApiRequestResult apiRequestResult=apiService.requestTestRun(api,getExtractionsList);
        apiRequestResultMapper.insertApiRequestResult(apiRequestResult);
        return apiRequestResult ;
    }
}
