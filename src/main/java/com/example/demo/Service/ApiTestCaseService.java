package com.example.demo.Service;

import com.example.demo.Model.ApiTestCase;
import com.example.demo.units.PageInfoNew;

public interface ApiTestCaseService {
    int insertApiTestCase(ApiTestCase apiTestCase);
    ApiTestCase findById(Integer id);
    PageInfoNew<ApiTestCase> findByPage(int pageNum,int pageSize);
}
