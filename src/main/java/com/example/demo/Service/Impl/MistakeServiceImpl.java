package com.example.demo.Service.Impl;

import com.example.demo.Mapper.MistakeMapper;
import com.example.demo.Model.Mistake;
import com.example.demo.Service.MistakeService;
import com.example.demo.utils.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MistakeServiceImpl implements MistakeService {
    @Resource
    private MistakeMapper mistakeMapper;

    @Override
    public int insertMistake(Mistake mistake){
        return mistakeMapper.insertMistake(mistake);
    }
    @Override
    public List<Mistake> findAll(){
        return mistakeMapper.findAll();
    }
    @Override
    public PageInfoNew<Mistake> finAllWithPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(mistakeMapper.findAll());
    }
    @Override
    public List<Mistake> findByDescriptionOrNot(String description){
        return mistakeMapper.findByDescriptionOrNot(description);
    }
}
