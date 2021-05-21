package com.example.demo.Model.TestData;

import lombok.Data;

@Data
public class FaultData {
    private String id;
    private String time;
    private String deviceCode;
    private String deviceType;
    private int group;
    private String sensorCode;
    private int sensorType;
    private int faultType;
    private String value;
}
