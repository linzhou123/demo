package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiTestCaseMapper;
import com.example.demo.Model.ApiTestCase;
import com.example.demo.Service.ApiTestCaseService;
import com.example.demo.units.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiTestCaseServiceImpl implements ApiTestCaseService {

    @Resource
    ApiTestCaseMapper apiTestCaseMapper;
    @Override
    public int insertApiTestCase(ApiTestCase apiTestCase){
        apiTestCase.setCreatTime((int)(System.currentTimeMillis()/1000));
        apiTestCase.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return apiTestCaseMapper.insertApiTestCase(apiTestCase);
    }
    @Override
    public ApiTestCase findById(Integer id){
        return apiTestCaseMapper.findById(id);
    }

    @Override
    public PageInfoNew<ApiTestCase> findByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(apiTestCaseMapper.findByAll());
    }
}
