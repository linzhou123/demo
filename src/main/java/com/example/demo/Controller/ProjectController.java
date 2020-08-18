package com.example.demo.Controller;

import com.example.demo.Model.Project;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    ProjectService projectService;
    /**
     * project添加接口
     * */
    @PostMapping("/add")
    public ResponseInfo insterProject(@RequestBody Project project){
        List<Project> projects =projectService.findByName(project.getProjectName());
        if (projects.size()>0){
            return ResponseInfo.errorInfo("项目名称:"+project.getProjectName()+"已存在");
        }
        projectService.insertProject(project);
        return ResponseInfo.successInfo("");
    }
    /**
     * 分页接口
     * */
    @GetMapping("/page")
    public ResponseInfo findAllProject(@RequestParam(value = "pageNum",required = true) int pageNum ,@RequestParam(value = "pageSize",required = true) int pageSize){
        return ResponseInfo.successInfo(projectService.finAllWithPage(pageNum,pageSize));
    }
    /**
     * project编写接口
     * */
    @PostMapping("/edit")
    public ResponseInfo updateProject(@RequestBody Project project){
        if (project.getId()==0){
            return ResponseInfo.errorInfo("id不能为空");
        }
        projectService.updateProject(project);
        return ResponseInfo.successInfo("");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseInfo deleteProject(@PathVariable("id") int projectId){
        List<Project> projects = projectService.findById(projectId);
        if (projectId==0){
            return ResponseInfo.errorInfo("请输入id");
        }
        else if (projects.size()==0){
            return ResponseInfo.errorInfo("项目不存在");
        }
        projectService.deleteById(projectId);
        return ResponseInfo.successInfo("");
    }
}
