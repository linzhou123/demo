package com.example.demo.Controller;

import com.example.demo.Model.Project;
import com.example.demo.Model.ResponseInfo;
import com.example.demo.Service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/project")
@Api(tags = "项目管理")
public class ProjectController {
    @Resource
    ProjectService projectService;
    /**
     * project添加接口
     * */
    @PostMapping("/add")
    @ApiOperation(value = "项目添加接口")
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
    @ApiOperation(value ="项目分页接口")
    public ResponseInfo getProjectPage(@RequestParam(value = "pageNum",required = true) int pageNum ,@RequestParam(value = "pageSize",required = true) int pageSize){
        return ResponseInfo.successInfo(projectService.finAllWithPage(pageNum,pageSize));
    }
    /**
     * project编写接口
     * */
    @PostMapping("/edit")
    @ApiOperation(value = "项目更新接口")
    public ResponseInfo updateProject(@RequestBody Project project){
        if (project.getId()==0){
            return ResponseInfo.errorInfo("id不能为空");
        }
        projectService.updateProject(project);
        return ResponseInfo.successInfo("");
    }
    /**
     * project删除接口
     * */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "项目删除接口")
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
    /**
     * 获取project所有的结果集
     * */
    @GetMapping("/list")
    @ApiOperation(value = "获取所有项目接口")
    public ResponseInfo getAllProject(){
        return ResponseInfo.successInfo(projectService.findAllProject());
    }
}
