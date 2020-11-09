package com.example.demo.Service.Impl;

import com.example.demo.Dto.ApiTestCaseMergeDto;
import com.example.demo.Mapper.ApiTestCaseMergeMapper;
import com.example.demo.Model.ApiTestCaseMerge;
import com.example.demo.Service.ApiTestCaseMergeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiTestCaseMergeServiceImpl implements ApiTestCaseMergeService {

    @Resource
    ApiTestCaseMergeMapper apiTestCaseMergeMapper;

    ApiTestCaseMerge apiTestCaseMerge=new ApiTestCaseMerge();
    @Override
    public void insertApiTestCaseMerge(ApiTestCaseMergeDto apiTestCaseMergeDto){
            for (int testCaseId :apiTestCaseMergeDto.getTestCaseIds()){
                apiTestCaseMerge.setTestCaseGroupId(apiTestCaseMergeDto.getGroupId());
                apiTestCaseMerge.setApiTestCaseId(testCaseId);
                apiTestCaseMergeMapper.insertApiTestCaseMerge(apiTestCaseMerge);
            }
    }
}
