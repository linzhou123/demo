package com.example.demo.Service;

import com.example.demo.Model.JobMsg;

public interface JobMsgService {
    int add(JobMsg jobMsg);

    int updateJob(JobMsg jobMsg);

    int deleteJob(int id);

    void modifyState(JobMsg jobMsg);

    void excJob(JobMsg jobMsg);
}
