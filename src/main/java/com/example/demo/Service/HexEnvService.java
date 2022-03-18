package com.example.demo.Service;

import com.example.demo.Model.HexEnv;

import java.util.List;

public interface HexEnvService {
    void insertHexEnv(HexEnv hexEnv);

    void updateHexEnv(HexEnv hexEnv);

    HexEnv getHexEnvById(int id);

    List<HexEnv> getAllHexEnvList();
}
