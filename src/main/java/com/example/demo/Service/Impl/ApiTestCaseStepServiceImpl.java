package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Mapper.ApiRequestResultMapper;
import com.example.demo.Mapper.ApiTestCaseResultMapper;
import com.example.demo.Mapper.ApiTestCaseStepMapper;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.ApiTestCaseResult;
import com.example.demo.Model.ApiTestCaseStep;
import com.example.demo.Service.ApiTestCaseStepService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.PageInfoNew;
import com.example.demo.utils.RestAssuredUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Slf4j
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
        //根据testCaseId获取step总数
        int sortIndex =apiTestCaseStepMapper.findByTestCaseId(apiTestCaseStepList.get(0).getTestCaseId()).size();
        for (ApiTestCaseStep apiTestCaseStep:apiTestCaseStepList){
            sortIndex+=1;
            apiTestCaseStep.setSort(sortIndex);
            apiTestCaseStepMapper.insertApiTestCaseStep(apiTestCaseStep);
        }
        return 1;
    }

    @Override
    public ApiTestCaseResultDto runStep(Integer testCaseId){
        List<ApiTestCaseStep> apiTestCaseSteps=apiTestCaseStepMapper.findByTestCaseId(testCaseId);
        ApiTestCaseResult apiTestCaseResult=new ApiTestCaseResult();
        int pass=0;
        int failed=0;
        int count =0;
        List<ApiRequestResult> apiRequestResults = new ArrayList<>();
        ApiTestCaseResultDto apiTestCaseResultDto =new ApiTestCaseResultDto();
        int stratTime =(int) System.currentTimeMillis();
        for (ApiTestCaseStep apiTestCaseStep:apiTestCaseSteps){
            RestAssuredUtil restAssuredUtil =new RestAssuredUtil(apiTestCaseStep);
            ApiRequestResult apiRequestResult= restAssuredUtil.requestCaseRun();
            log.info(JSON.toJSONString(apiRequestResult));
            apiRequestResults.add(apiRequestResult);
            apiRequestResultMapper.insertApiRequestResult(apiRequestResult);
            count+=1;
            if (apiRequestResult.isResultIsPass()){
                pass+=1;
            }else {
                failed+=1;
            }
        }
        int endTime =(int) System.currentTimeMillis();
        apiTestCaseResult.setTestCaseId(testCaseId);
        apiTestCaseResult.setCountReults(count);
        apiTestCaseResult.setPassReults(pass);
        apiTestCaseResult.setFailedReults(failed);
        apiTestCaseResult.setCreatTime(DateToStamp.getTimeStap());
        apiTestCaseResult.setUpdateTime(DateToStamp.getTimeStap());
        apiTestCaseResultMapper.insertResult(apiTestCaseResult);
        apiTestCaseResultDto.setApiRequestResults(apiRequestResults);
        apiTestCaseResultDto.setApiTestCaseResult(apiTestCaseResult);
        apiTestCaseResultDto.setRunTime(endTime-stratTime);
        return apiTestCaseResultDto;
    }

    @Override
    public void apiTestCaseStepEdit(List<ApiTestCaseStep> apiTestCaseStepList){
        int sortIndex =0;
        for (ApiTestCaseStep apiTestCaseStep:apiTestCaseStepList){
            sortIndex+=1;
            apiTestCaseStep.setSort(sortIndex);
            apiTestCaseStep.setUpdateTime(DateToStamp.getTimeStap());
            apiTestCaseStepMapper.updateApiTestCaseStep(apiTestCaseStep);
        }
    }

    @Override
    public void apiTestCaseStepDelete(int StepId) {
        apiTestCaseStepMapper.deleteStepByStepId(StepId);
    }

    @Override
    public PageInfoNew<ApiTestCaseStep> findTestCaseStepPageByTestCaseId(int pageNum, int pageSize, int testCaseId){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(apiTestCaseStepMapper.findByTestCaseId(testCaseId));
    }

    @Override
    public  List<ApiTestCaseStep> findStepListByTestCaseId(int testCaseId){
        return apiTestCaseStepMapper.findByTestCaseId(testCaseId);
    }




}
