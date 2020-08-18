package com.example.demo.Service;

import com.example.demo.Model.ApiSuite;
import com.example.demo.units.PageInfoNew;

import java.util.List;

public interface ApiSuiteService {
    int insertApiSuite(ApiSuite apiSuite);
    PageInfoNew<ApiSuite> findAllWithPage(int pageNum,int pageSize);
    List<ApiSuite> findAll();
    List<ApiSuite> findAllByName(String apiSuiteName);
    List<ApiSuite> findAllById(int apiSuiteId);
}
