package com.example.demo.Service;

import com.example.demo.Model.HexData;
import com.example.demo.Model.SendHexRecord;
import com.example.demo.utils.PageInfoNew;

import java.util.List;

public interface HexDataService {
    void insertHexData(HexData hexData);

    SendHexRecord debugHexData(Integer id,String username,String password,String host, Integer port);

    List<SendHexRecord> runHexData(String username,String password,String host,Integer port);

    PageInfoNew<HexData> getHexPage(int pageNum, int pageSize);

    void deleteHex(int id);
}
