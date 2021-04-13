package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;
@Data
public class SendHexRecord implements Serializable {
    private int id;
    private String url;
    private String hexContent;
    private String sysBackContent;
    private int creatTime;
}
