package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Model.TestData.Gcu400;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tpson")
@Slf4j
public class TpsonTestController {

    @GetMapping("/open/device/log/gcu400")
    public ResponseInfo gcu400(@RequestBody Gcu400 gcu400){
        log.info("获取数据：" + JSONObject.toJSONString(gcu400));
        return ResponseInfo.successInfo(gcu400);
    }
}
