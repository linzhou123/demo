package com.example.demo.Dto;

import com.example.demo.Model.HexData;
import lombok.Data;

@Data
public class HexDto extends HexData {
    private String userName;
    private String password;
}
