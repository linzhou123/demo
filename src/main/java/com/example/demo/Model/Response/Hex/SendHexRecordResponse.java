package com.example.demo.Model.Response.Hex;

import com.example.demo.Model.SendHexRecord;
import lombok.Data;

@Data
public class SendHexRecordResponse extends SendHexRecord {

    private String deviceName;

    private static final long serialVersionUID = 1L;
}
