package com.example.demo.Model;

import java.util.List;

public class Api {
    private int id;
    private String name;
    private int projectId;
    private int apiSuiteId;
    private List<Header> requestHeader;
    private String method;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getApiSuiteId() {
        return apiSuiteId;
    }

    public void setApiSuiteId(int apiSuiteId) {
        this.apiSuiteId = apiSuiteId;
    }

    public List<Header> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(List<Header> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
