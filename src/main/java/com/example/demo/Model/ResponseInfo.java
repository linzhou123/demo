package com.example.demo.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseInfo implements Serializable {
    private Integer code;
    private Object data;
    private String message;
    private Boolean success;

    private static final long serialVersionUID = 1L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseInfo() {

    }

    public ResponseInfo(Integer code, String message, Object data, Boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public static ResponseInfo successInfo(Object data) {
        return new ResponseInfo(200, "操作成功", data, true);
    }

    public static ResponseInfo errorInfo(String message) {
        return new ResponseInfo(520, message, "", false);
    }


}
