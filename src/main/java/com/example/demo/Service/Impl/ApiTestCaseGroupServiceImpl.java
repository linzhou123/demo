package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiTestCaseGroupMapper;
import com.example.demo.Model.ApiTestCaseGroup;
import com.example.demo.Service.ApiTestCaseGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiTestCaseGroupServiceImpl implements ApiTestCaseGroupService {

    @Resource
    ApiTestCaseGroupMapper apiTestCaseGroupMapper;

    @Override
    public int insertApiTestCaseGroup(ApiTestCaseGroup apiTestCaseGroup){
        return apiTestCaseGroupMapper.insertApiTestCaseGroup(apiTestCaseGroup);
    }
}
