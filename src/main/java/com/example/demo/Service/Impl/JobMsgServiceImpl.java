package com.example.demo.Service.Impl;

import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Enums.PlanStatusEnum;
import com.example.demo.Mapper.*;
import com.example.demo.Model.*;
import com.example.demo.Service.ApiTestCaseStepService;
import com.example.demo.Service.JobMsgService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.SchedulerUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class JobMsgServiceImpl implements JobMsgService {

    @Resource
    Scheduler scheduler;
    @Resource
    JobMsgMapper jobMsgMapper;
    @Resource
    JobPlanLogMapper jobPlanLogMapper;

    @Resource
    ApiRequestResultMapper apiRequestResultMapper;

    @Resource
    ApiTestCaseResultMapper apiTestCaseResultMapper;

    @Resource
    ApiTestCaseMergeMapper apiTestCaseMergeMapper;

    @Resource
    ApiTestCaseStepService apiTestCaseStepService;

    @Override
    public int add(JobMsg jobMsg) {
        jobMsg.setCreateTime(DateToStamp.getTimeStap());
        jobMsg.setUpdateTime(DateToStamp.getTimeStap());
        int rows = jobMsgMapper.add(jobMsg);
        if (rows > 0) {
            log.info("添加返回" + String.valueOf(rows));
            SchedulerUtil.createScheduler(scheduler, jobMsg);
        }
        return rows;
    }

    @Override
    public int updateJob(JobMsg jobMsg) {
        jobMsg.setUpdateTime(DateToStamp.getTimeStap());
        int row = jobMsgMapper.updateJob(jobMsg);
        if (row > 0) {
            SchedulerUtil.updateJob(scheduler, jobMsg);
        }
        return row;
    }

    @Override
    public int deleteJob(int id) {
        int row = jobMsgMapper.deleteJobById(id);
        if (row > 0) {
            SchedulerUtil.deleteJob(scheduler, id);
        }
        return 0;
    }

    @Override
    public void excJob(JobMsg jobMsg) {
        int count = 0, pass = 0, failed = 0;
        JobPlanLog jobPlanLog = new JobPlanLog();
        List<Integer> groupIdList = jobMsg.getGroupIdList();
        count = groupIdList.size();
        jobPlanLog.setGroupTotal(count);
        jobPlanLog.setFailedTotal(0);
        jobPlanLog.setPassTotal(0);
        jobPlanLog.setStatus(PlanStatusEnum.ONGOING_STATUS.getValue());
        jobPlanLog.setCreateTime(DateToStamp.getTimeStap());
        jobPlanLogMapper.insert(jobPlanLog);
        for (int groupId : groupIdList) {
            ApiTestCaseResultDto apiTestCaseResultDto = runTestGroup(groupId);
            ApiTestCaseResult apiTestCaseResult = apiTestCaseResultDto.getApiTestCaseResult();
            apiTestCaseResult.setPlanId(jobPlanLog.getId());
            apiTestCaseResultMapper.insertResult(apiTestCaseResult);
            if (apiTestCaseResult.getFailedResults() > 0) {
                pass += 1;
            } else {
                failed += 1;
            }
            for (ApiRequestResult apiRequestResult : apiTestCaseResultDto.getApiRequestResults()) {
                apiRequestResult.setPlanId(jobPlanLog.getId());
                apiRequestResultMapper.insertApiRequestResult(apiRequestResult);
            }
        }
        jobPlanLog.setFailedTotal(failed);
        jobPlanLog.setPassTotal(pass);
        if (failed == 0) {
            jobPlanLog.setStatus(PlanStatusEnum.PASS_STATUS.getValue());
        } else {
            jobPlanLog.setStatus(PlanStatusEnum.FAILED_STATUS.getValue());
        }
        jobPlanLogMapper.update(jobPlanLog);
    }

    public ApiTestCaseResultDto runTestGroup(int testCaseGroupId) {
        ApiTestCaseResultDto apiTestCaseResultDto = new ApiTestCaseResultDto();
        List<ApiTestCaseMerge> apiTestCaseMergeList = apiTestCaseMergeMapper.findByGroupId(testCaseGroupId);
        for (ApiTestCaseMerge apiTestCaseMerge : apiTestCaseMergeList) {
            apiTestCaseResultDto = apiTestCaseStepService.runStep(apiTestCaseMerge.getApiTestCaseId());

        }
        return apiTestCaseResultDto;
    }

}
