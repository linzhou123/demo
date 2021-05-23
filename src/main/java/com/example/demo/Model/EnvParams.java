package com.example.demo.Model;

import com.example.demo.Model.ApiModel.EnvGParams;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EnvParams implements Serializable {
    private int id;
    //0代表String类型 1代表int类型 2代表Boolean类型
    private int envId;
    private int projectId;
    private int type;
    private String name;
    private List<EnvGParams> value;
    private int createTime;
    private int updateTime;

    private static final long serialVersionUID = 1L;
}
