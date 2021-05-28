package com.example.demo.Mapper;

import com.example.demo.Model.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    int insertProject(Project project);

    int updateProject(Project project);

    int deleteById(@Param(value = "projectId") int projectId);

    List<Project> findById(@Param(value = "projectId") int project);

    List<Project> findAllProject();

    List<Project> findByName(@Param(value = "projectName") String projectName);
}
