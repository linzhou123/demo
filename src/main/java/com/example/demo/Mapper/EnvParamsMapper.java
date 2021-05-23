package com.example.demo.Mapper;

import com.example.demo.Model.EnvParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnvParamsMapper {
    void insert(EnvParams envParams);
    List<EnvParams> selectByProjectId(@Param(value = "projectId") int projectId);
    EnvParams selectByName(@Param(value = "name") String name);

}
