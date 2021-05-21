package com.example.demo.Service.Impl;

import com.example.demo.Dto.HexDto;
import com.example.demo.Mapper.HexDataMapper;
import com.example.demo.Mapper.SendHexRecordMapper;
import com.example.demo.Model.HexData;
import com.example.demo.Model.SendHexRecord;
import com.example.demo.Service.HexDataService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.HexUtil;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HexDataServiceImpl implements HexDataService {

    @Resource
    HexDataMapper hexDataMapper;

    @Resource
    SendHexRecordMapper sendHexRecordMapper;

    @Override
    public void insertHexData(HexData hexData) {
        hexData.setCreatTime(DateToStamp.getTimeStap());
        hexData.setUpdateTime(DateToStamp.getTimeStap());
        hexDataMapper.insertHexData(hexData);
    }

    @Override
    public SendHexRecord debugHexData(Integer id, String username, String password, String host, Integer port) {
        HexDto data = hexDataMapper.getHexDataById(id);
        data.setUserName(username);
        data.setPassword(password);
        if (!StringUtils.isEmpty(host) || !StringUtils.isEmpty(port)) {
            data.setHost(host);
            data.setPort(port);
        }
        SendHexRecord sendHexRecord = new SendHexRecord();
        HexUtil hexUtil = new HexUtil(data);
        try {
            String content = hexUtil.getBackMsg(data.getHexContent());
            sendHexRecord.setIsPass(hexUtil.getHexAssert());
            sendHexRecord.setHexContent(data.getHexContent());
            sendHexRecord.setSysBackContent(content);
            sendHexRecord.setCreatTime(DateToStamp.getTimeStap());
            sendHexRecord.setUrl(data.getHost().concat(":").concat(String.valueOf(data.getPort())));
            sendHexRecordMapper.insertHexRecord(sendHexRecord);
            return sendHexRecord;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sendHexRecord;
    }

    @Override
    public List<SendHexRecord> runHexData(String username, String password, String host, Integer port) {
        List<SendHexRecord> sendHexRecordList = new ArrayList<>();
        List<HexData> hexDataList = hexDataMapper.getAllHexData();
        for (HexData hexData : hexDataList) {
            SendHexRecord sendHexRecord = debugHexData(hexData.getId(), username, password, host, port);
            sendHexRecordList.add(sendHexRecord);
        }
        return sendHexRecordList;
    }

    @Override
    public PageInfoNew<HexData> getHexPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfoNew<>(hexDataMapper.getAllHexData());
    }

    @Override
    public void deleteHex(int id) {
        hexDataMapper.deleteHex(id);
    }
}
