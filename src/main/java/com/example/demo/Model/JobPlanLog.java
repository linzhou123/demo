package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class JobPlanLog implements Serializable {
    private int id ;
    @ApiModelProperty(value ="任务id")
    private int jobId;
    @ApiModelProperty(value = "计划状态")
    private  int status;
    @ApiModelProperty(value = "用例集总数")
    private int groupTotal;
    @ApiModelProperty(value = "成功用例集总数")
    private int passTotal;
    @ApiModelProperty(value = "失败用例集总数")
    private int failedTotal;
    @ApiModelProperty(value = "创建时间")
    private int createTime;

    private static final long serialVersionUID = 1L;

}
