package com.example.demo.Model.Response.Hex;

import com.example.demo.Model.HexData;
import lombok.Data;

@Data
public class HexPageResp extends HexData {
    private String dateTypeName;
    private String deviceTypeName;
}
