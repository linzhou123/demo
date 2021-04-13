package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Project implements Serializable {
    @ApiModelProperty(value = "项目id")
    private int id;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "详细描述")
    private String description;
    @ApiModelProperty(value = "创建时间")
    private int creatTime;
    @ApiModelProperty(value = "更新时间")
    private int updateTime;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
