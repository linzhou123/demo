package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiTestCaseMerge implements Serializable {
    private int id;
    private int testCaseGroupId;
    private int apiTestCaseId;
}
