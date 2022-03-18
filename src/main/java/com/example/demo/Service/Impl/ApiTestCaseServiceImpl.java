package com.example.demo.Service.Impl;

import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Mapper.ApiRequestResultMapper;
import com.example.demo.Mapper.ApiTestCaseMapper;
import com.example.demo.Mapper.ApiTestCaseResultMapper;
import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.ApiTestCase;
import com.example.demo.Service.ApiTestCaseService;
import com.example.demo.Service.ApiTestCaseStepService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiTestCaseServiceImpl implements ApiTestCaseService {

    @Resource
    ApiTestCaseMapper apiTestCaseMapper;

    @Resource
    ApiTestCaseResultMapper apiTestCaseResultMapper;

    @Resource
    ApiRequestResultMapper apiRequestResultMapper;

    @Resource
    ApiTestCaseStepService apiTestCaseStepService;

    @Override
    public int insertApiTestCase(ApiTestCase apiTestCase) {
        apiTestCase.setCreateTime(DateToStamp.getTimeStap());
        apiTestCase.setUpdateTime(DateToStamp.getTimeStap());
        return apiTestCaseMapper.insertApiTestCase(apiTestCase);
    }

    @Override
    public ApiTestCase findById(Integer id) {
        return apiTestCaseMapper.findById(id);
    }

    @Override
    public void updateApiTestCase(ApiTestCase apiTestCase) {
        apiTestCase.setUpdateTime(DateToStamp.getTimeStap());
        apiTestCaseMapper.updateApiTestCase(apiTestCase);
    }

    @Override
    public PageInfoNew<ApiTestCase> findByPage(int pageNum, int pageSize, Integer projectId, String name) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfoNew<>(apiTestCaseMapper.findByPage(projectId, name));
    }

    @Override
    public void testCaseDelete(int testCaseId) {
        apiTestCaseMapper.testCaseDelete(testCaseId);
    }

    @Override
    public ApiTestCaseResultDto runCase(Integer testCaseId, Integer envId) {
        ApiTestCaseResultDto apiTestCaseResultDto = apiTestCaseStepService.runStep(testCaseId,envId);
        apiTestCaseResultMapper.insertResult(apiTestCaseResultDto.getApiTestCaseResult());
        for (ApiRequestResult apiRequestResult : apiTestCaseResultDto.getApiRequestResults()) {
            apiRequestResultMapper.insertApiRequestResult(apiRequestResult);
        }
        return apiTestCaseResultDto;
    }
}
