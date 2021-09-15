package com.example.demo.Mapper;

import com.example.demo.Dto.HexDto;
import com.example.demo.Model.HexData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HexDataMapper {
    void insertHexData(HexData hexData);

    void updateHexData(HexData hexData);

    List<HexData> getAllHexData();

    HexDto getHexDataById(@Param(value = "id") int id);

    void deleteHex(@Param(value = "id") int id);
}
