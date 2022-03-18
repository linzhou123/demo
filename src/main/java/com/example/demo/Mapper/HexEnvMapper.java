package com.example.demo.Mapper;

import com.example.demo.Model.HexEnv;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HexEnvMapper {
    void insertHexEnv(HexEnv hexEnv);

    void updateHexEnv(HexEnv hexEnv);

    List<HexEnv> getAllHexEnv();

    HexEnv getHexEnvById(@Param(value = "id") int id);

    void deleteHexEnv(@Param(value = "id") int id);
}
