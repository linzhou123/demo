package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 Linzhou
 * @创建时间 2021/10/29
 * @描述
 **/
@Data
public class User implements Serializable {

    private int id;
    private String name;
    private String userName;
    private String password;
    private int createTime;
    private int updateTime;

    private static final long serialVersionUID = 1L;
}
