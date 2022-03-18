package com.example.demo.Service;

import com.example.demo.Model.ApiTestCaseGroup;
import com.example.demo.Model.Response.TestCaseGroup.GroupResp;

import java.util.List;

public interface ApiTestCaseGroupService {
    int insertApiTestCaseGroup(ApiTestCaseGroup apiTestCaseGroup);

    void runTestGroup(int testCaseGroupId,int envId);

    List<ApiTestCaseGroup> apiTestCaseGroupList(int projectId,String groupName);

    List<GroupResp> caseListByGroupId(int groupId);
}
