package com.example.demo.Controller;

import com.example.demo.Model.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
public class TestController {

    @PostMapping("/test")
    public ResponseInfo test(){
        return ResponseInfo.successInfo("正常");
    }

}
