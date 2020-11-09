package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiTestCaseGroupMapper;
import com.example.demo.Mapper.ApiTestCaseMapper;
import com.example.demo.Mapper.ApiTestCaseMergeMapper;
import com.example.demo.Model.ApiTestCaseGroup;
import com.example.demo.Model.ApiTestCaseMerge;
import com.example.demo.Service.ApiTestCaseGroupService;
import com.example.demo.Service.ApiTestCaseStepService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiTestCaseGroupServiceImpl implements ApiTestCaseGroupService {

    @Resource
    ApiTestCaseGroupMapper apiTestCaseGroupMapper;

    @Resource
    ApiTestCaseMergeMapper apiTestCaseMergeMapper;

    @Resource
    ApiTestCaseMapper apiTestCaseMapper;

    @Resource
    ApiTestCaseStepService apiTestCaseStepService;

    @Override
    public int insertApiTestCaseGroup(ApiTestCaseGroup apiTestCaseGroup){
        apiTestCaseGroup.setCreatTime((int)(System.currentTimeMillis()/1000));
        apiTestCaseGroup.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return apiTestCaseGroupMapper.insertApiTestCaseGroup(apiTestCaseGroup);
    }

    @Override
    public void runTestGroup(int testCaseGroudId){

        List<ApiTestCaseMerge> apiTestCaseMergeList=  apiTestCaseMergeMapper.findByGroupId(testCaseGroudId);
        for (ApiTestCaseMerge apiTestCaseMerge: apiTestCaseMergeList){
            apiTestCaseStepService.runStep(apiTestCaseMerge.getApiTestCaseId());
        }
    }
}
