package com.example.demo.Controller;

import com.example.demo.Model.ApiSuite;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ApiSuiteService;
import com.example.demo.Service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/apiSuite")
public class ApiSuiteController {
    @Resource
    private ApiSuiteService apiSuiteService;
    @Resource
    private ProjectService projectService;

    @PostMapping("/add")
    public ResponseInfo insertApiSuite(@RequestBody ApiSuite apiSuite){
        List<ApiSuite> apiSuites =apiSuiteService.findAllByName(apiSuite.getName());
        if (apiSuites.size()>0){
            return ResponseInfo.errorInfo("name已重复");
        }
        apiSuiteService.insertApiSuite(apiSuite);
        return ResponseInfo.successInfo("");
    }
    @GetMapping("/page")
    public ResponseInfo finAllWithPage(@RequestParam(value = "pageNum",required = true) int pageNum,@RequestParam(value = "pageSize",required = true) int pageSize ){
        return ResponseInfo.successInfo(apiSuiteService.findAllWithPage(pageNum,pageSize));
    }
}

