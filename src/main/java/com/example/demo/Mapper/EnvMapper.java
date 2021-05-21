package com.example.demo.Mapper;

import com.example.demo.Model.Env;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnvMapper {
    void insert (Env env);
    List<Env> selectByProjectId(@Param(value = "projectId") int projectId);
    Env selectById(@Param(value = "id") int id);
    void updateEnv(Env env);
}
