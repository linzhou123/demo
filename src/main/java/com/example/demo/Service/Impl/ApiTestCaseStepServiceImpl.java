package com.example.demo.Service.Impl;

import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Mapper.ApiRequestResultMapper;
import com.example.demo.Mapper.ApiTestCaseMapper;
import com.example.demo.Mapper.ApiTestCaseResultMapper;
import com.example.demo.Mapper.ApiTestCaseStepMapper;
import com.example.demo.Model.*;
import com.example.demo.Model.ApiModel.GetExtractions;
import com.example.demo.Service.ApiService;
import com.example.demo.Service.ApiTestCaseStepService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Resource
    ApiTestCaseMapper apiTestCaseMapper;

    @Resource
    ApiService apiService;

    @Override
    public int insertStepToTestCase(List<ApiTestCaseStep> apiTestCaseStepList) {
        //根据testCaseId获取step总数
        int sortIndex = apiTestCaseStepMapper.findByTestCaseId(apiTestCaseStepList.get(0).getTestCaseId()).size();
        for (ApiTestCaseStep apiTestCaseStep : apiTestCaseStepList) {
            sortIndex += 1;
            apiTestCaseStep.setSort(sortIndex);
            apiTestCaseStepMapper.insertApiTestCaseStep(apiTestCaseStep);
        }
        return 1;
    }

    @Override
    public ApiTestCaseResultDto runStep(Integer testCaseId) {
        List<ApiTestCaseStep> apiTestCaseSteps = apiTestCaseStepMapper.findByTestCaseId(testCaseId);
        ApiTestCase apiTestCase = apiTestCaseMapper.findById(testCaseId);
        ApiTestCaseResult apiTestCaseResult = new ApiTestCaseResult();
        int pass = 0;
        int failed = 0;
        int count = 0;
        List<ApiRequestResult> apiRequestResults = new ArrayList<>();
        List<GetExtractions> getExtractionsList = new ArrayList<>();
        ApiTestCaseResultDto apiTestCaseResultDto = new ApiTestCaseResultDto();
        int startTime = (int) System.currentTimeMillis();
        for (ApiTestCaseStep apiTestCaseStep : apiTestCaseSteps) {
            log.info("获取测试用例步骤:" + apiTestCaseStep.toString());
            Api api = new Api();
            BeanUtils.copyProperties(apiTestCaseStep, api);
            ApiRequestResult apiRequestResult = apiService.requestTestRun(api, getExtractionsList);
            apiRequestResult.setApiId(apiTestCaseStep.getApiId());
            apiRequestResult.setApiTestCaseId(apiTestCaseStep.getTestCaseId());
            apiRequestResult.setApiTestCaseStepId(apiTestCaseStep.getId());
            getExtractionsList.addAll(apiRequestResult.getResultExtractions());
            apiRequestResults.add(apiRequestResult);
//            apiRequestResultMapper.insertApiRequestResult(apiRequestResult);
            count += 1;
            if (apiRequestResult.isResultIsPass()) {
                pass += 1;
            } else {
                failed += 1;
            }
        }
        log.info("获取测试用例结果集:" + apiRequestResults);
        int endTime = (int) System.currentTimeMillis();
        apiTestCaseResult.setTestCaseId(testCaseId);
        apiTestCaseResult.setCountResults(count);
        apiTestCaseResult.setPassResults(pass);
        apiTestCaseResult.setFailedResults(failed);
        apiTestCaseResult.setCreateTime(DateToStamp.getTimeStap());
        apiTestCaseResult.setUpdateTime(DateToStamp.getTimeStap());
        apiTestCaseResult.setTestRunTime(endTime - startTime);
//        apiTestCaseResultMapper.insertResult(apiTestCaseResult);
        apiTestCaseResultDto.setApiRequestResults(apiRequestResults);
        apiTestCaseResultDto.setApiTestCaseResult(apiTestCaseResult);
        apiTestCaseResultDto.setCaseName(apiTestCase.getName());
        return apiTestCaseResultDto;
    }

    @Override
    public void apiTestCaseStepEdit(List<ApiTestCaseStep> apiTestCaseStepList) {
        int sortIndex = 0;
        for (ApiTestCaseStep apiTestCaseStep : apiTestCaseStepList) {
            sortIndex += 1;
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
    public void apiTestCaseStepsDelete(List<Integer> ids) {
        for (int id : ids) {
            apiTestCaseStepMapper.deleteStepByStepId(id);
        }
    }

    @Override
    public PageInfoNew<ApiTestCaseStep> findTestCaseStepPageByTestCaseId(int pageNum, int pageSize, int testCaseId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfoNew<>(apiTestCaseStepMapper.findByTestCaseId(testCaseId));
    }

    @Override
    public List<ApiTestCaseStep> findStepListByTestCaseId(int testCaseId) {
        return apiTestCaseStepMapper.findByTestCaseId(testCaseId);
    }


}
