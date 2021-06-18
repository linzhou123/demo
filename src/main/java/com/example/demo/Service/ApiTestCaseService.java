package com.example.demo.Service;

import com.example.demo.Dto.ApiTestCaseResultDto;
import com.example.demo.Model.ApiTestCase;
import com.example.demo.utils.PageInfoNew;

public interface ApiTestCaseService {
    int insertApiTestCase(ApiTestCase apiTestCase);

    void updateApiTestCase(ApiTestCase apiTestCase);

    ApiTestCase findById(Integer id);

    PageInfoNew<ApiTestCase> findByPage(int pageNum, int pageSize, Integer projectId, String name);

    void testCaseDelete(int testCaseId);

    ApiTestCaseResultDto runCase(Integer testCaseId);
}
