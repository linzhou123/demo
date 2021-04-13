package com.example.demo.Dto;

import com.example.demo.Model.ApiTestCase;
import com.example.demo.Model.ApiTestCaseStep;
import lombok.Data;

import java.util.List;

@Data
public class ApiTestCaseDto extends ApiTestCase {
    List<ApiTestCaseStep> apiTestCaseStepList;
}
