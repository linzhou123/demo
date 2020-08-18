package com.example.demo.Model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Mistake implements Serializable {
    private int id;
    @NotBlank(message = "name不能为空")
    private String name;
    private int type ;
    @NotBlank(message = "description不能为空")
    private String description;
    private int mistakeLevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMistakeLevel() {
        return mistakeLevel;
    }

    public void setMistakeLevel(int mistakeLevel) {
        this.mistakeLevel = mistakeLevel;
    }
}
