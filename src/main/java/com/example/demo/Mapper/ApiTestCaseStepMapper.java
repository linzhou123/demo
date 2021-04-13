package com.example.demo.Mapper;

import com.example.demo.Model.ApiTestCaseStep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiTestCaseStepMapper {
    int insertApiTestCaseStep(ApiTestCaseStep apiTestCaseStep);
    int updateApiTestCaseStep(ApiTestCaseStep apiTestCaseStep);
    void deleteStepByStepId(@Param(value = "id")int id);
    List<ApiTestCaseStep> findByTestCaseId(@Param(value = "testCaseId") Integer testCaseId);

}
