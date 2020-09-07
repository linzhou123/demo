package com.example.demo.Mapper;

import com.example.demo.Model.ApiTestCase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiTestCaseMapper {
    int insertApiTestCase(ApiTestCase apiTestCase);
    ApiTestCase findById(@Param(value = "id") Integer id);
    List<ApiTestCase> findByAll();
}
