package com.example.demo.Model;

import lombok.Data;

@Data
public class ApiTestCaseGroupResult {
    private int id;
    private int testCaseGroupId;
    private int countResult;
    private int passResult;
    private int failedResult;
    private int createTime;
    private int updateTime;
}
