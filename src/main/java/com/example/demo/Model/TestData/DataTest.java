package com.example.demo.Model.TestData;

import lombok.Data;

import java.util.List;

@Data
public class DataTest {
    private String id;
    private String time;
    private String deviceCode;
    private String deviceType;
    private List<Object> data;


}


