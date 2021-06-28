package com.example.demo.common.ExceptionHandler;


import com.example.demo.Model.ResponseInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = IllegalAccessException.class)
    public ResponseInfo handlerException(IllegalAccessException e){
        return ResponseInfo.errorInfo(e.getMessage());
    }
}
