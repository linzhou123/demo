package com.example.demo.common.ExceptionHandler;


import com.example.demo.Model.ResponseInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseInfo handlerException(IllegalAccessException e){
        return ResponseInfo.errorInfo(e.getMessage());
    }
}
