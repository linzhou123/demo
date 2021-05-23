package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Dto.ApiSuiteTreeDto;
import com.example.demo.Mapper.ApiMapper;
import com.example.demo.Mapper.ApiSuiteMapper;
import com.example.demo.Model.ApiSuite;
import com.example.demo.Service.ApiSuiteService;
import com.example.demo.utils.DateToStamp;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ApiSuiteServiceImpl implements ApiSuiteService {
    @Resource
    private ApiSuiteMapper apiSuiteMapper;

    @Resource
    private ApiMapper apiMapper;

    @Override
    public int insertApiSuite(ApiSuite apiSuite){
        apiSuite.setCreateTime(DateToStamp.getTimeStap());
        apiSuite.setUpdateTime(DateToStamp.getTimeStap());
        return apiSuiteMapper.insertApiSuite(apiSuite);
    }

    @Override
    public int updateApiSuite(ApiSuite apiSuite){
        apiSuite.setUpdateTime(DateToStamp.getTimeStap());
        return apiSuiteMapper.updateApiSuite(apiSuite);
    }

    @Override
    public int deleteById(int apiSuiteId){
        return apiSuiteMapper.deleteById(apiSuiteId);
    }

    @Override
    public List<ApiSuite> findAllByName(String apiSuiteName){
        return  apiSuiteMapper.findAllByName(apiSuiteName);
    }

    @Override
    public ApiSuite findAllById(int apiSuiteId){
        return  apiSuiteMapper.findAllById(apiSuiteId);
    }

    @Override
    public List<ApiSuite> findAll(){
        return apiSuiteMapper.findAll();
    }
    @Override
    public List<ApiSuite> findByProjectId(Integer projectId){
        return apiSuiteMapper.findByProjectId(projectId);
    }
    @Override
    public List<ApiSuiteTreeDto> findTreeDtoByProjectId(int projectId){
        List<ApiSuiteTreeDto> apiSuiteTreeDtoList = apiSuiteMapper.findTreeByProjectId(projectId);
        log.info(JSON.toJSONString(apiSuiteTreeDtoList));
        for (ApiSuiteTreeDto apiSuiteTreeDto :apiSuiteTreeDtoList){
            apiSuiteTreeDto.setApiList(apiMapper.findAllByApiSuiteId(apiSuiteTreeDto.getId()));
        }
        return apiSuiteTreeDtoList;
    }

    @Override
    public PageInfoNew<ApiSuite> findAllWithPage(int pageNum,int pageSize,int projetctId){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(apiSuiteMapper.findByProjectId(projetctId));
    }

    @Override
    public JSONObject js(int apiSuiteId){
        ApiSuite apiSuites = apiSuiteMapper.findAllById(apiSuiteId);
        return JSONObject.parseObject(JSON.toJSONString(apiSuites));
    }
}
