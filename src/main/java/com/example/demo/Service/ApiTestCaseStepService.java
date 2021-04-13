package com.example.demo.Service;

import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Model.ApiTestCaseStep;
import com.example.demo.utils.PageInfoNew;

import java.util.List;

public interface ApiTestCaseStepService {
    int insertStepToTestCase(List<ApiTestCaseStep> apiTestCaseStepList);
    ApiTestCaseResultDto runStep(Integer testCaseId);
    void apiTestCaseStepEdit(List<ApiTestCaseStep> apiTestCaseStepList);
    void apiTestCaseStepDelete(int StepId);
    PageInfoNew<ApiTestCaseStep> findTestCaseStepPageByTestCaseId(int pageNum, int pageSize,int testCaseId);
    List<ApiTestCaseStep> findStepListByTestCaseId(int testCaseId);
}