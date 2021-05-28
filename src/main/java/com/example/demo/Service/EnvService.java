package com.example.demo.Service;

import com.example.demo.Model.Env;

import java.util.List;

public interface EnvService {
    void insert(Env env);

    List<Env> selectByProjectId(int projectId);

    Env selectById(int id);

    void updateEnv(Env env);
}
