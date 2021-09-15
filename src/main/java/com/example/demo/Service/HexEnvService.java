package com.example.demo.Service;

import com.example.demo.Model.HexEnv;

public interface HexEnvService {
    void insertHexEnv(HexEnv hexEnv);

    void updateHexEnv(HexEnv hexEnv);

    HexEnv getHexEnvById(int id);
}
