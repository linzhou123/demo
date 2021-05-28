package com.example.demo.Model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class Mistake implements Serializable {
    private int id;
    @NotBlank(message = "name不能为空")
    private String name;
    private int type;
    @NotBlank(message = "description不能为空")
    private String description;
    private int mistakeLevel;

    private static final long serialVersionUID = 1L;
}
