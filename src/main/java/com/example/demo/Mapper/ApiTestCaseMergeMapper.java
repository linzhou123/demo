package com.example.demo.Mapper;

import com.example.demo.Model.ApiTestCaseMerge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiTestCaseMergeMapper {
    void insertApiTestCaseMerge(ApiTestCaseMerge apiTestCaseMerge);
    List<ApiTestCaseMerge> findByGroupId(@Param(value = "testCaseGroupId") int testCaseGroupId);
}
