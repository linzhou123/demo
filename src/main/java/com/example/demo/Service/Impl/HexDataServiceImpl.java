package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dto.HexDto;
import com.example.demo.Enums.HexDataTypeEnum;
import com.example.demo.Enums.HexDeviceTypeEnum;
import com.example.demo.Mapper.HexDataMapper;
import com.example.demo.Mapper.HexEnvMapper;
import com.example.demo.Mapper.SendHexRecordMapper;
import com.example.demo.Model.HexData;
import com.example.demo.Model.HexEnv;
import com.example.demo.Model.Response.Hex.HexPageResp;
import com.example.demo.Model.Response.Hex.SendHexRecordResponse;
import com.example.demo.Model.TestData.PageParams;
import com.example.demo.Service.HexDataService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.HexUtil;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HexDataServiceImpl implements HexDataService {

    @Resource
    HexDataMapper hexDataMapper;

    @Resource
    SendHexRecordMapper sendHexRecordMapper;

    @Resource
    HexEnvMapper hexEnvMapper;

    @Value("${webhook}")
    private String dingDingTalkUrl;

    @Override
    public void insertHexData(HexData hexData) {
        hexData.setCreateTime(DateToStamp.getTimeStap());
        hexData.setUpdateTime(DateToStamp.getTimeStap());
        hexDataMapper.insertHexData(hexData);
    }

    @Override
    public void updateHexData(HexData hexData) {
        hexData.setUpdateTime(DateToStamp.getTimeStap());
        hexDataMapper.updateHexData(hexData);
    }

    @Override
    public SendHexRecordResponse debugHexData(Integer id, int hexEnvId) {
        HexDto data = hexDataMapper.getHexDataById(id);
        HexEnv hexEnv =hexEnvMapper.getHexEnvById(hexEnvId);
        data.setUserName(hexEnv.getPlatformUser());
        data.setPassword(hexEnv.getPlatformPassword());
        data.setSendPlatform(hexEnv.getSendPlatform());
        if (!StringUtils.isEmpty(hexEnv.getHost()) || !StringUtils.isEmpty(hexEnv.getPort())) {
            data.setHost(hexEnv.getHost());
            data.setPort(hexEnv.getPort());
        }
        SendHexRecordResponse sendHexRecord = new SendHexRecordResponse();
        HexUtil hexUtil = new HexUtil(data);
        try {
            String content = hexUtil.getBackMsg(data.getHexContent());
            sendHexRecord.setDeviceName(data.getDeviceName());
            sendHexRecord.setIsPass(hexUtil.getHexAssert());
            sendHexRecord.setHexContent(data.getHexContent());
            sendHexRecord.setSysBackContent(content);
            sendHexRecord.setCreateTime(DateToStamp.getTimeStap());
            sendHexRecord.setUrl(data.getHost().concat(":").concat(String.valueOf(data.getPort())));
            sendHexRecordMapper.insertHexRecord(sendHexRecord);
//            return sendHexRecord;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (hexUtil.getHexAssert()){
            sedDingDingTalk(data.getDeviceName()+"设备发送测试hex流成功");
        }else {
            sedDingDingTalk(data.getDeviceName()+"设备发送测试hex流失败");
        }
        return sendHexRecord;
    }

    @Override
    public List<SendHexRecordResponse> runHexData(Integer hexEnvId) {
        List<SendHexRecordResponse> sendHexRecordList = new ArrayList<>();
        List<HexData> hexDataList = hexDataMapper.getAllHexData();
        for (HexData hexData : hexDataList) {
            SendHexRecordResponse sendHexRecord = debugHexData(hexData.getId(),hexEnvId);
            sendHexRecordList.add(sendHexRecord);
        }

        return sendHexRecordList;
    }

    @Override
    public PageInfoNew<HexPageResp> getHexPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<HexPageResp> hexDataList =new ArrayList<>();
        List<HexData> list = hexDataMapper.getAllHexData();
        PageInfo<HexData> pageInfo = new PageInfo<>(list);
        for (HexData hexData :list){
            HexPageResp resp =new HexPageResp();
            BeanUtils.copyProperties(hexData,resp);
            resp.setDateTypeName(HexDataTypeEnum.valueOfType(hexData.getDataType()).getName());
            resp.setDeviceTypeName(HexDeviceTypeEnum.valueOfType(hexData.getDeviceType()).getName());
            hexDataList.add(resp);
        }
        PageParams pageParams = new PageParams();
        pageParams.setPageNum(pageInfo.getPageNum());
        pageParams.setPages(pageInfo.getPages());
        pageParams.setPageSize(pageInfo.getPageSize());
        pageParams.setPageTotal(pageInfo.getTotal());
        pageParams.setSize(pageInfo.getSize());
//        System.out.println(hexDataList);
        return new PageInfoNew<>(hexDataList,pageParams);
    }

    @Override
    public void deleteHex(int id) {
        hexDataMapper.deleteHex(id);
    }

    public void sedDingDingTalk(String content){
        Map<String,String> map = new HashMap<>();
        map.put("content",content);
        Map<String,Object> reqMap =new HashMap<>();
        reqMap.put("msgtype","text");
        reqMap.put("text",map);
        JSONObject jsonObject  = new JSONObject(reqMap);
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        Response response= RestAssured.given().headers(headers).body(jsonObject).when().post(dingDingTalkUrl);
        log.info("钉钉消息推送结果："+response.prettyPrint());

    }
}
