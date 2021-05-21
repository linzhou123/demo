package com.example.demo.Model;

import lombok.Data;

@Data
public class ApiTestCaseResult {
    private int id;
    private int testCaseId;
    private int countResults;
    private int passResults;
    private int failedResults;
    private int testRunTime;
    private int creatTime;
    private int updateTime;

    private static final long serialVersionUID = 1L;

}
