package com.example.demo.Dto;

import com.example.demo.Model.ApiRequestResult;
import com.example.demo.Model.ApiTestCaseResult;
import lombok.Data;

import java.util.List;

@Data
public class ApiTestCaseResultDto {
    ApiTestCaseResult apiTestCaseResult;
    List<ApiRequestResult> apiRequestResults;
    String caseName;
    private static final long serialVersionUID = 1L;
}
