package com.example.demo.Dto;

import lombok.Data;

import java.util.List;
@Data
public class ApiTestCaseMergeDto {
    private int groupId;
    private List<Integer> testCaseIds;
}
