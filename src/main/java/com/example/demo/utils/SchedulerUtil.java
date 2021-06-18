package com.example.demo.utils;

import com.example.demo.Model.JobMsg;
import com.example.demo.Task.SchedulerTask;
import com.example.demo.common.contant.ScheduleConstants;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
public class SchedulerUtil {


    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(int jobId) {
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(int jobId) {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, int jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("getCronTrigger 异常：", e);
        }
        return null;
    }


    public static void createScheduler(Scheduler scheduler, JobMsg jobMsg) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(SchedulerTask.class).withIdentity(getJobKey(jobMsg.getId())).build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobMsg.getCronExpression());

            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobMsg.getId())).withSchedule(cronScheduleBuilder).build();
            jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, jobMsg);
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("进入createScheduler方法中");
            if (jobMsg.getStatus() == ScheduleConstants.Status.PAUSE.getValue()) {
                pauseJob(scheduler, jobMsg.getId());
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }


    public static void updateJob(Scheduler scheduler, JobMsg jobMsg) {
        try {
            TriggerKey triggerKey = getTriggerKey(jobMsg.getId());

            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobMsg.getCronExpression());

            CronTrigger trigger = getCronTrigger(scheduler, jobMsg.getId());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

            // 参数
            trigger.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, jobMsg);

            scheduler.rescheduleJob(triggerKey, trigger);

            // 暂停任务
            if (jobMsg.getStatus() == ScheduleConstants.Status.PAUSE.getValue()) {
                pauseJob(scheduler, jobMsg.getId());
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    public static void pauseJob(Scheduler scheduler, int jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    public static void resumeJob(Scheduler scheduler, int jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    public static void deleteJob(Scheduler scheduler, int jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }
}
