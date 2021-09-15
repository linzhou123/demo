package com.example.demo.Service.Impl;

import com.example.demo.Mapper.HexEnvMapper;
import com.example.demo.Model.HexEnv;
import com.example.demo.Service.HexEnvService;
import com.example.demo.utils.DateToStamp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HexEnvServiceImpl implements HexEnvService {

    @Resource
    HexEnvMapper hexEnvMapper;
    @Override
    public void insertHexEnv(HexEnv hexEnv) {
        hexEnv.setCreateTime(DateToStamp.getTimeStap());
        hexEnv.setUpdateTime(DateToStamp.getTimeStap());
        hexEnvMapper.insertHexEnv(hexEnv);
    }

    @Override
    public void updateHexEnv(HexEnv hexEnv) {
        hexEnv.setUpdateTime(DateToStamp.getTimeStap());
        hexEnvMapper.updateHexEnv(hexEnv);
    }

    @Override
    public HexEnv getHexEnvById(int id) {
        return hexEnvMapper.getHexEnvById(id);
    }
}
