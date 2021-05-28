package com.example.demo.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dto.ApiSuiteTreeDto;
import com.example.demo.Model.ApiSuite;
import com.example.demo.utils.PageInfoNew;

import java.util.List;

public interface ApiSuiteService {
    int insertApiSuite(ApiSuite apiSuite);

    int updateApiSuite(ApiSuite apiSuite);

    int deleteById(int apiSuiteId);

    PageInfoNew<ApiSuite> findAllWithPage(int pageNum, int pageSize, int projetctId);

    List<ApiSuite> findAll();

    List<ApiSuite> findByProjectId(Integer projectId);

    List<ApiSuiteTreeDto> findTreeDtoByProjectId(int projectId);

    List<ApiSuite> findAllByName(String apiSuiteName);

    ApiSuite findAllById(int apiSuiteId);

    JSONObject js(int apiSuiteId);
}
