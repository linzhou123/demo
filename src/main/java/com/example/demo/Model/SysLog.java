package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysLog implements Serializable {


    private int id;
    private String url;
    private String method;
    private String params;
    private String header;
    private String classMethod;
    private int code;
    private String response;
    private int createTime;
    private static final long serialVersionUID = 1L;
}
