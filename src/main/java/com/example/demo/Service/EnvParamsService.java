package com.example.demo.Service;

import com.example.demo.Model.EnvParams;
import com.example.demo.utils.PageInfoNew;

public interface EnvParamsService {
    void insert(EnvParams envParams);
    PageInfoNew<EnvParams> selectByProjectId(int pageNum,int pageSize,int projectId);
}
