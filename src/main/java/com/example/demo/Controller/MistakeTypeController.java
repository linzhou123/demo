package com.example.demo.Controller;

import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.MistakeTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mistakeType")
public class MistakeTypeController {
    @Resource
    private MistakeTypeService mistakeTypeService;
    @GetMapping("/findall")
    public ResponseInfo findall(){
        return ResponseInfo.successInfo(mistakeTypeService.getMistakeTypeAll());
    }
}
