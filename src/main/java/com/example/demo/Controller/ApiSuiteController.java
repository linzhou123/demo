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
    public ResponseInfo insertApiSuite(@RequestBody ApiSuite apiSuite) {
        List<ApiSuite> apiSuites = apiSuiteService.findAllByName(apiSuite.getName());
        if (apiSuites.size() > 0) {
            return ResponseInfo.errorInfo("apiSuiteName已重复");
        }
        apiSuiteService.insertApiSuite(apiSuite);
        return ResponseInfo.successInfo("");
    }

    /**
     * 更新apiSuite
     */
    @PostMapping("/edit")
    public ResponseInfo updateApiSuite(@RequestBody ApiSuite apiSuite) {
        ApiSuite apiSuites = apiSuiteService.findAllById(apiSuite.getId());
        if (apiSuites.getId() == 0) {
            return ResponseInfo.errorInfo("apiSuite不存在");
        }
        apiSuiteService.updateApiSuite(apiSuite);
        return ResponseInfo.successInfo("");
    }

    /**
     * 删除apiSuite
     */
    @DeleteMapping("/delete/{id}")
    public ResponseInfo deleteApiSuiteById(@PathVariable(value = "id") int apiSuiteId) {
        apiSuiteService.deleteById(apiSuiteId);
        return ResponseInfo.successInfo("");
    }
    /**
     * apiSuite 分页接口
     */
    @GetMapping("/page")
    public ResponseInfo finAllWithPage(@RequestParam(value = "pageNum", required = true) int pageNum, @RequestParam(value = "pageSize", required = true) int pageSize) {
        return ResponseInfo.successInfo(apiSuiteService.findAllWithPage(pageNum, pageSize));
    }
    /**
     * api详情获取接口
     * */
    @GetMapping("/get/{id}")
    public ResponseInfo finById(@PathVariable(value = "id") int apiSuiteId){
        return ResponseInfo.successInfo(apiSuiteService.findAllById(apiSuiteId));
    }
}

