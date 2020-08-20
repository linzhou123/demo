package com.example.demo.Service;

import com.example.demo.Model.Api;

import java.util.List;

public interface ApiService {
    int insertApi(Api api);
    List<Api> findAllByName(String apiName);
    Api findById(int apiId);
    int updateApi(Api api);
}
