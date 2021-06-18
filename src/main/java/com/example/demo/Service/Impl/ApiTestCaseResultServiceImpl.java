package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiTestCaseResultMapper;
import com.example.demo.Model.ApiTestCaseResult;
import com.example.demo.Service.ApiTestCaseResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiTestCaseResultServiceImpl implements ApiTestCaseResultService {

    @Resource
    ApiTestCaseResultMapper apiTestCaseResultMapper;

    @Override
    public int insertResult(ApiTestCaseResult apiTestCaseResult) {
        int row = apiTestCaseResultMapper.insertResult(apiTestCaseResult);
        return row;
    }
}
