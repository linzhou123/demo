package com.example.demo.Dto;

import lombok.Data;

@Data
public class JobResultDto extends ApiTestCaseResultDto {
    private int groupTotal;
    private int passTotal;
    private int failedTotal;
    private static final long serialVersionUID = 1L;
}
