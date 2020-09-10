package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiRequestResultMapper;
import com.example.demo.Mapper.ApiTestCaseResultMapper;
import com.example.demo.Mapper.ApiTestCaseStepMapper;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.ApiTestCaseResult;
import com.example.demo.Model.ApiTestCaseStep;
import com.example.demo.Service.ApiTestCaseStepService;
import com.example.demo.units.RestAssuredUnit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApiTestCaseStepServiceImpl implements ApiTestCaseStepService {

    @Resource
    ApiTestCaseStepMapper apiTestCaseStepMapper;

    @Resource
    ApiRequestResultMapper apiRequestResultMapper;

    @Resource
    ApiTestCaseResultMapper apiTestCaseResultMapper;

    @Override
    public int insertStepToTestCase(List<ApiTestCaseStep> apiTestCaseStepList){
        int sortIndex =0;
        for (ApiTestCaseStep apiTestCaseStep:apiTestCaseStepList){
            sortIndex+=1;
            apiTestCaseStep.setSort(sortIndex);
            apiTestCaseStepMapper.insertApiTestCaseStep(apiTestCaseStep);
        }
        return 1;
    }

    @Override
    public int runStep(Integer testCaseId){
        List<ApiTestCaseStep> apiTestCaseSteps=apiTestCaseStepMapper.findByTestCaseId(testCaseId);
        ApiTestCaseResult apiTestCaseResult=new ApiTestCaseResult();
        int pass=0;
        int failed=0;
        int count =0;
        for (ApiTestCaseStep apiTestCaseStep:apiTestCaseSteps){
            RestAssuredUnit restAssuredUnit=new RestAssuredUnit(apiTestCaseStep);
            ApiRequestResult apiRequestResult=restAssuredUnit.requestCaseRun();
            apiRequestResultMapper.insertApiRequestResult(apiRequestResult);
            count+=1;
            if (apiRequestResult.isResultIsPass()){
                pass+=1;
            }else {
                failed+=1;
            }
        }
        apiTestCaseResult.setTestCaseId(testCaseId);
        apiTestCaseResult.setCountReults(count);
        apiTestCaseResult.setPassReults(pass);
        apiTestCaseResult.setFailedReults(failed);
        apiTestCaseResult.setCreatTime((int) (System.currentTimeMillis() / 1000));
        apiTestCaseResult.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return apiTestCaseResultMapper.insertResult(apiTestCaseResult);
    }



}
