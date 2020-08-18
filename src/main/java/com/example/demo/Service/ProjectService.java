package com.example.demo.Service;

import com.example.demo.Model.Project;
import com.example.demo.units.PageInfoNew;

import java.util.List;

public interface ProjectService {
    int insertProject(Project project);
    int updateProject(Project project);
    int deleteById(int projectId);
    PageInfoNew<Project> finAllWithPage(int pageNum, int pageSize);
    List<Project> findById(int projectId);
    List<Project> findAllProject();
    List<Project> findByName(String projectName);
}
