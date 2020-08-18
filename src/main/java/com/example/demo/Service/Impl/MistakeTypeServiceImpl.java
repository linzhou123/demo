package com.example.demo.Service.Impl;

import com.example.demo.Mapper.MistakeTypeMapper;
import com.example.demo.Model.MistakeType;
import com.example.demo.Service.MistakeTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MistakeTypeServiceImpl implements MistakeTypeService {
    @Resource
    private MistakeTypeMapper mistakeTypeMapper;

    @Override
    public List<MistakeType> getMistakeTypeAll(){
        return mistakeTypeMapper.getMistakeTypeAll();
    }

}
