package com.example.demo.Service.Impl;

import com.example.demo.Mapper.ProjectMapper;
import com.example.demo.Model.Project;
import com.example.demo.Service.ProjectService;
import com.example.demo.units.PageInfoNew;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectMapper projectMapper;


    @Override
    public int insertProject(Project project){
        project.setCreatTime((int)(System.currentTimeMillis()/1000));
        project.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return projectMapper.insertProject(project);
    }

    @Override
    public int updateProject(Project project){
        project.setUpdateTime((int)(System.currentTimeMillis()/1000));
        return projectMapper.updateProject(project);
    }

    @Override
    public List<Project> findById(int projectId){
        return projectMapper.findById(projectId);
    }

    @Override
    public List<Project> findAllProject(){
        return projectMapper.findAllProject();
    }

    @Override
    public List<Project> findByName(String projectName){
        return projectMapper.findByName(projectName);
    }

    @Override
    public int deleteById(int projectId){
        return projectMapper.deleteById(projectId);
    }

    @Override
    public PageInfoNew<Project> finAllWithPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfoNew<>(projectMapper.findAllProject());
    }
}
