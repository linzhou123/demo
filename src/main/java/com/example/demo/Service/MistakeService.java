package com.example.demo.Service;


import com.example.demo.Model.Mistake;
import com.example.demo.utils.PageInfoNew;

import java.util.List;

public interface MistakeService {


    int insertMistake(Mistake mistake);

    List<Mistake> findByDescriptionOrNot(String description);

    List<Mistake> findAll();

    PageInfoNew<Mistake> finAllWithPage(int pageNum, int pageSize);
}
