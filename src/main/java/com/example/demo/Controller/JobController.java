package com.example.demo.Controller;

import com.example.demo.Model.JobMsg;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.JobMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/job")
@Api(tags = "任务管理接口")
public class JobController {

    @Resource
    JobMsgService jobMsgService;

    @PostMapping("/add")
    @ApiOperation(value = "添加任务接口")
    public ResponseInfo add(@RequestBody JobMsg jobMsg){
        if (CronExpression.isValidExpression(jobMsg.getCronExpression())&&!jobMsg.getCronExpression().isEmpty()){
            jobMsgService.add(jobMsg);
            return ResponseInfo.successInfo("");
        }else {
            log.error(jobMsg.getCronExpression());
            return ResponseInfo.errorInfo("时间表达式有误");
        }
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新任务接口")
    public ResponseInfo update(@RequestBody JobMsg jobMsg){
        jobMsgService.updateJob(jobMsg);
        return ResponseInfo.successInfo("");
    }
    @PostMapping("/delete")
    @ApiOperation(value = "删除任务接口")
    @ApiImplicitParam(value = "任务id", name = "id",required = true,dataType = "int")
    public ResponseInfo delete(int id){
        jobMsgService.deleteJob(id);
        return ResponseInfo.successInfo("");
    }
}
