package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MistakeType implements Serializable {

    private int id;

    private int mistakeType;

    private String mistakeName;

    private static final long serialVersionUID = 1L;


}
