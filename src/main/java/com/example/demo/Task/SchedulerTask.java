package com.example.demo.Task;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.JobMsg;
import com.example.demo.Service.JobMsgService;
import com.example.demo.common.contant.ScheduleConstants;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulerTask extends QuartzJobBean implements Job {

    @Autowired
    JobMsgService jobMsgService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobMsg jobMsg = new JobMsg();
        jobMsg = (JobMsg) context.getJobDetail().getJobDataMap().get(ScheduleConstants.TASK_PROPERTIES);
        log.info("-------进入定时任务执行计划中-------");
        log.info("传入参数：" + JSONObject.toJSONString(jobMsg));
        jobMsgService.excJob(jobMsg);
        log.info("-------计划执行完成-------");
//        log.info("-----test-----,时间:"+ DateToStamp.getTimeStap());
    }
}
