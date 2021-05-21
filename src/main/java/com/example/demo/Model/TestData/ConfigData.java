package com.example.demo.Model.TestData;

import lombok.Data;

import java.util.List;

@Data
public class ConfigData {
    private String id;
    private String time;
    private String deviceCode;
    private List<Object> data;
}
