package com.example.demo.Model.TestData;

import lombok.Data;

@Data
public class PageParams {
    private int pageNum;
    private int pageSize;
    private long pageTotal;
    private int size;
    private int pages;
}
