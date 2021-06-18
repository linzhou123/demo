package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JobMsg implements Serializable {
    private int id;
    private List<Integer> groupIdList;
    private String jobName;
    private int envId;
    private String cronExpression;
    private String remark;
    private int status ;
    private boolean isSendEmail;
    private String emailList;
    private int createTime;
    private int updateTime;

    private static final long serialVersionUID = 1L;

}
