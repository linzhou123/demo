package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Model.Api;
import com.example.demo.Service.ApiService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

    @Resource
    ApiMapper apiMapper;

    @Override
    public int insertApi(Api api) {
        if (Objects.isNull(api.getRequestAssert())) {
            api.setRequestAssert(Collections.emptyList());
        }
        api.setCreateTime(DateToStamp.getTimeStap());
        api.setUpdateTime(DateToStamp.getTimeStap());
        log.info("添加api："+JSON.toJSONString(api));
        return apiMapper.insertApi(api);
    }

    @Override
    public List<Api> findAllByName(String apiName) {
        return apiMapper.findAllByName(apiName);
    }

    @Override
    public List<Api> findBySuiteId(Integer apiSuiteId) {
        return apiMapper.findAllByApiSuiteId(apiSuiteId);
    }

    @Override
    public Api findById(int apiId) {
        return apiMapper.findById(apiId);
    }

    @Override
    public PageInfoNew<Api> findAllWithPage(int pageNum, int pageSize, Integer apsuiteId, Integer projectId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfoNew<>(apiMapper.findAllToPage(apsuiteId, projectId));
    }

    @Override
    public int updateApi(Api api) {
        return apiMapper.updateApi(api);
    }

    @Override
    public void deleteApi(int id) {
        apiMapper.deleteApi(id);
    }
}
