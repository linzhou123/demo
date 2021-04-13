package com.example.demo.Model;

public class ApiTestCaseResult {
    private int id;
    private int testCaseId;
    private int countReults;
    private int passReults;
    private int failedReults;
    private int creatTime;
    private int updateTime;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public int getCountReults() {
        return countReults;
    }

    public void setCountReults(int countReults) {
        this.countReults = countReults;
    }

    public int getPassReults() {
        return passReults;
    }

    public void setPassReults(int passReults) {
        this.passReults = passReults;
    }

    public int getFailedReults() {
        return failedReults;
    }

    public void setFailedReults(int failedReults) {
        this.failedReults = failedReults;
    }

    public int getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(int creatTime) {
        this.creatTime = creatTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
