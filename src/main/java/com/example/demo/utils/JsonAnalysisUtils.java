package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.Api;

import java.util.List;

public class JsonAnalysisUtils {

    public List<Api> getApi(String fileName){
        JSONObject js = FileUtils.readJsonFile(fileName);
        return null;
    }
}
