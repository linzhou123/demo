package com.example.demo.Model.TestData;

import lombok.Data;

@Data
public class TestData  {
    private int gasValue;
    private int status;
    private int realValue;
    private int configValue;
    private int alarmStatus;
    private String alarmTypeDesc;
    private int rsrp;
    private int snr;
    private int alarmThresholdValue;
    private int uploadPeriod;
}
