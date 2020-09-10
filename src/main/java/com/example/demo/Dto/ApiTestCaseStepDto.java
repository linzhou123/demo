package com.example.demo.Dto;

import com.example.demo.Model.Api;
import com.example.demo.Model.ApiTestCaseStep;

public class ApiTestCaseStepDto extends ApiTestCaseStep {
    private Api api;

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }
}
