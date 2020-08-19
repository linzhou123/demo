package com.example.demo.Controller;

import com.example.demo.Model.Api;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    ApiService apiService;

    @PostMapping("/add")
    public ResponseInfo insertApi(@RequestBody Api api){
        List<Api> apiList =apiService.findAllByName(api.getName());
        if (apiList.size()>0){
            return ResponseInfo.errorInfo(api.getName()+"已存在");
        }
        apiService.insertApi(api);
        return ResponseInfo.successInfo("");
    }
}
