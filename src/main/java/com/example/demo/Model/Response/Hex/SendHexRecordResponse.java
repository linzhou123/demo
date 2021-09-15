package com.example.demo.Model.Response;

import com.example.demo.Model.SendHexRecord;
import lombok.Data;

@Data
public class SendHexRecordResponse extends SendHexRecord {

    private String deviceName;
    private String dateType;
    private String deviceType;

    private static final long serialVersionUID = 1L;
}
