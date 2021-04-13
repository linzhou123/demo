package com.example.demo.Dto;

import com.example.demo.Model.Api;
import com.example.demo.Model.ApiSuite;
import lombok.Data;

import java.util.List;

@Data
public class ApiSuiteTreeDto extends ApiSuite {
    private static final long serialVersionUID = 1L;
    List<Api> apiList;
}
