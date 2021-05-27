package com.example.demo.Model.ApiModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetExtractions {
    @ApiModelProperty(value = "提取参数后名称")
    private String extractName;
    @ApiModelProperty(value = "返回值")
    private String value;
}
