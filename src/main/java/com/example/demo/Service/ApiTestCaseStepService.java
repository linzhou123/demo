package com.example.demo.Service;

import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Model.ApiTestCaseStep;
import com.example.demo.utils.PageInfoNew;

import java.util.List;

public interface ApiTestCaseStepService {
    int insertStepToTestCase(List<ApiTestCaseStep> apiTestCaseStepList);

    ApiTestCaseResultDto runStep(Integer testCaseId,Integer envId);

    void apiTestCaseStepEdit(List<ApiTestCaseStep> apiTestCaseStepList);

    void apiTestCaseStepDelete(int StepId);

    void apiTestCaseStepsDelete(List<Integer> ids);

    PageInfoNew<ApiTestCaseStep> findTestCaseStepPageByTestCaseId(int pageNum, int pageSize, int testCaseId);

    List<ApiTestCaseStep> findStepListByTestCaseId(int testCaseId);
}
