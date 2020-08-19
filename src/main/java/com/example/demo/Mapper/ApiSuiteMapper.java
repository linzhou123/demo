package com.example.demo.Mapper;

import com.example.demo.Model.ApiSuite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiSuiteMapper {
    int insertApiSuite(ApiSuite apiSuite);
    int updateApiSuite(ApiSuite apiSuite);
    int deleteById(@Param(value = "apiSuiteId") int apiSuiteId);
    List<ApiSuite> findAll();
    List<ApiSuite> findAllByName(@Param(value = "apiSuiteName") String apiSuiteName);
    ApiSuite findAllById(@Param(value = "apiSuiteId") int apiSuiteId);
}
