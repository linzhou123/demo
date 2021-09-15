package com.example.demo.Service;

import com.example.demo.Model.HexData;
import com.example.demo.Model.Response.Hex.HexPageResp;
import com.example.demo.Model.Response.Hex.SendHexRecordResponse;
import com.example.demo.utils.PageInfoNew;

import java.util.List;

public interface HexDataService {
    void insertHexData(HexData hexData);
    void updateHexData(HexData hexData);

    SendHexRecordResponse debugHexData(Integer id, int hexEnvId);

    List<SendHexRecordResponse> runHexData(Integer hexEnvId);

    PageInfoNew<HexPageResp> getHexPage(int pageNum, int pageSize);

    void deleteHex(int id);
}
