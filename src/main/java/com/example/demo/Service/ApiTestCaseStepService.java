package com.example.demo.Service;

import com.example.demo.Model.ApiTestCaseStep;

import java.util.List;

public interface ApiTestCaseStepService {
    int insertStepToTestCase(List<ApiTestCaseStep> apiTestCaseStepList);
    int runStep(Integer testCaseId);
}
