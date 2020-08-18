package com.example.demo.Model;

import java.io.Serializable;
public class MistakeType implements Serializable {

    private int id;

    private int mistakeType;

    private String mistakeName;

    public void setId(int id) {
        this.id = id;
    }

    public void setMistakeType(int mistakeType) {
        this.mistakeType = mistakeType;
    }

    public void setMistakeName(String mistakeName) {
        this.mistakeName = mistakeName;
    }

    public int getId() {
        return id;
    }

    public int getMistakeType() {
        return mistakeType;
    }

    public String getMistakeName() {
        return mistakeName;
    }


}
