package com.example.demo.Controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/envParams")
@Api(tags = "环境变量参数管理")
public class EnvParamsController {
}
