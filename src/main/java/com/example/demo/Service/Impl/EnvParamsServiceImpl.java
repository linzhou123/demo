package com.example.demo.Service.Impl;

import com.example.demo.Mapper.EnvParamsMapper;
import com.example.demo.Model.EnvParams;
import com.example.demo.Service.EnvParamsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EnvParamsServiceImpl implements EnvParamsService {
    @Resource
    EnvParamsMapper envParamsMapper;

    @Override
    public void insert(EnvParams envParams) {
        envParamsMapper.insert(envParams);
    }
}
