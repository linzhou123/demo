package com.example.demo.Service.Impl;

import com.example.demo.Mapper.EnvMapper;
import com.example.demo.Model.Env;
import com.example.demo.Service.EnvService;
import com.example.demo.utils.DateToStamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EnvServiceImpl implements EnvService {

    @Resource
    EnvMapper envMapper;

    @Override
    public void insert(Env env) {
        env.setCreateTime(DateToStamp.getTimeStap());
        env.setUpdateTime(DateToStamp.getTimeStap());
        envMapper.insert(env);
    }

    @Override
    public List<Env> selectByProjectId(int projectId) {
        return envMapper.selectByProjectId(projectId);
    }

    @Override
    public Env selectById(int id) {
        return envMapper.selectById(id);
    }

    @Override
    public void updateEnv(Env env) {
        envMapper.updateEnv(env);
    }
}
