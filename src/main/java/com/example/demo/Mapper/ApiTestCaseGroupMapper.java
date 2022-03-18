package com.example.demo.Mapper;

import com.example.demo.Model.ApiTestCaseGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiTestCaseGroupMapper {
    int insertApiTestCaseGroup(ApiTestCaseGroup apiTestCaseGroup);
    List<ApiTestCaseGroup> getGroupByProjectIdAndName(@Param(value = "projectId") int projectId,@Param(value = "name") String groupName);
}
