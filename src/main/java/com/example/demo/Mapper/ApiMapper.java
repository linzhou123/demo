package com.example.demo.Mapper;

import com.example.demo.Model.Api;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApiMapper {

    int insertApi(Api api);
    List<Api> findAllByName(@Param("apiName") String apiName);
}
