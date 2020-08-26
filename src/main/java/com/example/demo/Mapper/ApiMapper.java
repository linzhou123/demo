package com.example.demo.Mapper;

import com.example.demo.Model.Api;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiMapper {

    int insertApi(Api api);
    List<Api> findAllByName(@Param(value = "apiName") String apiName);
    List<Api> findAllByApiSuiteId(@Param(value = "apiSuiteId") Integer apiSuiteId);
    Api findById(@Param(value = "apiId") int apiId);
    int updateApi(Api api);
    List<Api> findAll();
}
