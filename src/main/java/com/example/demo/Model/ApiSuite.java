package com.example.demo.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiSuite implements Serializable {
    @ApiModelProperty(value = "api类别id")
    private Integer id;
    @ApiModelProperty(value = "api类别名称")
    private String name;
    @ApiModelProperty(value = "项目id")
    private Integer projectId;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建时间")
    private Integer createTime;
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    private static final long serialVersionUID = 1L;


}
