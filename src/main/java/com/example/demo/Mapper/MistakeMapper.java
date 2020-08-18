package com.example.demo.Mapper;

import com.example.demo.Model.Mistake;

import java.util.List;

public interface MistakeMapper {

    int insertMistake(Mistake mistake);
    List<Mistake> findByDescriptionOrNot(String description);
    List<Mistake> findAll();
}
