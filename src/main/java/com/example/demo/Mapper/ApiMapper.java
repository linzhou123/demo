package com.example.demo.Mapper;

import com.example.demo.Model.Api;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiMapper {

    int insertApi(Api api);

    List<Api> findAllByName(@Param(value = "apiName") String apiName);

    List<Api> findAllByApiSuiteId(@Param(value = "apiSuiteId") Integer apiSuiteId);

    List<Api> findAllToPage(@Param(value = "apiSuiteId") Integer apiSuiteId, @Param(value = "projectId") Integer projectId);

    Api findById(@Param(value = "apiId") int apiId);

    int updateApi(Api api);

    List<Api> findAll();

    void deleteApi(@Param(value = "id") int id);
}
