package com.example.demo.Service;

import com.example.demo.Model.Api;
import com.example.demo.units.PageInfoNew;

import java.util.List;

public interface ApiService {
    int insertApi(Api api);
    List<Api> findAllByName(String apiName);
    PageInfoNew<Api> findAllWithPage(int pageNum, int pageSize,Integer apiSuiteId);
    Api findById(int apiId);
    int updateApi(Api api);
}
