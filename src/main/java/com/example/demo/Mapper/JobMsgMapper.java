package com.example.demo.Mapper;

import com.example.demo.Model.JobMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMsgMapper {
    int add(JobMsg jobMsg);

    List<JobMsg> selectAll();

    int updateJob(JobMsg jobMsg);

    int deleteJobById(@Param(value = "id") int id);
}
