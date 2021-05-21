package com.example.demo.Service.Impl;

import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Mapper.ApiTestCaseGroupMapper;
import com.example.demo.Mapper.ApiTestCaseGroupResultMapper;
import com.example.demo.Mapper.ApiTestCaseMapper;
import com.example.demo.Mapper.ApiTestCaseMergeMapper;
import com.example.demo.Model.ApiTestCaseGroup;
import com.example.demo.Model.ApiTestCaseGroupResult;
import com.example.demo.Model.ApiTestCaseMerge;
import com.example.demo.Service.ApiTestCaseGroupService;
import com.example.demo.Service.ApiTestCaseStepService;
import com.example.demo.utils.DateToStamp;
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
    @Resource
    ApiTestCaseGroupResultMapper apiTestCaseGroupResultMapper;

    ApiTestCaseGroupResult apiTestCaseGroupResult=new ApiTestCaseGroupResult();
    @Override
    public int insertApiTestCaseGroup(ApiTestCaseGroup apiTestCaseGroup){
        apiTestCaseGroup.setCreatTime(DateToStamp.getTimeStap());
        apiTestCaseGroup.setUpdateTime(DateToStamp.getTimeStap());
        return apiTestCaseGroupMapper.insertApiTestCaseGroup(apiTestCaseGroup);
    }

    @Override
    public void runTestGroup(int testCaseGroupId){

        int count=0,pass = 0,failed =0;
        List<ApiTestCaseMerge> apiTestCaseMergeList=  apiTestCaseMergeMapper.findByGroupId(testCaseGroupId);
        count=apiTestCaseMergeList.size();
        apiTestCaseGroupResult.setCountResult(count);
        for (ApiTestCaseMerge apiTestCaseMerge: apiTestCaseMergeList){
           ApiTestCaseResultDto apiTestCaseResultDto =apiTestCaseStepService.runStep(apiTestCaseMerge.getApiTestCaseId());
           if (apiTestCaseResultDto.getApiTestCaseResult().getFailedResults()>0){
               failed+=1;
           }else{
               pass+=1;
           }
        }
        apiTestCaseGroupResult.setTestCaseGroupId(testCaseGroupId);
        apiTestCaseGroupResult.setFailedResult(failed);
        apiTestCaseGroupResult.setPassResult(pass);
        apiTestCaseGroupResult.setCreatTime(DateToStamp.getTimeStap());
        apiTestCaseGroupResult.setUpdateTime(DateToStamp.getTimeStap());
        apiTestCaseGroupResultMapper.insertResult(apiTestCaseGroupResult);
    }
}
