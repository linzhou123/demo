package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiTestCaseResult  implements Serializable {
    private int id;
    private int testCaseId;
    private int countResults;
    private int passResults;
    private int failedResults;
    private int testRunTime;
    private int createTime;
    private int updateTime;

    private static final long serialVersionUID = 1L;

}
