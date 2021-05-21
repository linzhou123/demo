package com.example.demo.Model;

import lombok.Data;

@Data
public class EnvParams {
    private int id;
    //0代表String类型 1代表int类型 2代表Boolean类型
    private int envId;
    private int projectId;
    private int type;
    private String name;
    private String value;
    private int createTime;
    private int updateTime;
}
