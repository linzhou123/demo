package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ApiTestCaseMapper;
import com.example.demo.Model.ApiTestCase;
import com.example.demo.Service.ApiTestCaseService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiTestCaseServiceImpl implements ApiTestCaseService {

    @Resource
    ApiTestCaseMapper apiTestCaseMapper;
    @Override
    public int insertApiTestCase(ApiTestCase apiTestCase){
        apiTestCase.setCreatTime(DateToStamp.getTimeStap());
        apiTestCase.setUpdateTime(DateToStamp.getTimeStap());
        return apiTestCaseMapper.insertApiTestCase(apiTestCase);
    }
    @Override
    public ApiTestCase findById(Integer id){
        return apiTestCaseMapper.findById(id);
    }

    @Override
    public void updateApiTestCase(ApiTestCase apiTestCase){
        apiTestCase.setUpdateTime(DateToStamp.getTimeStap());
        apiTestCaseMapper.updateApiTestCase(apiTestCase);
    }

    @Override
    public PageInfoNew<ApiTestCase> findByPage(int pageNum, int pageSize, Integer projectId,String name){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(apiTestCaseMapper.findByPage(projectId,name));
    }
    @Override
    public void testCaseDelete(int testCaseId){
        apiTestCaseMapper.testCaseDelete(testCaseId);
    }
}
