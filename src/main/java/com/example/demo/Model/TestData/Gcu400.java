package com.example.demo.Model.TestData;

import lombok.Data;

import java.io.Serializable;
@Data
public class Gcu400 implements Serializable {
    private int time ;
    private String deviceType;
    private String deviceCode;
    private String dataType;
    private TestData data;
    private static final long serialVersionUID = 1L;
}
