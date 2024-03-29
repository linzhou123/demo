package com.example.demo.utils;

import com.example.demo.Model.TestData.PageParams;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PageInfoNew<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;

    private long total;
    //总页数
    private int pages;
    //结果集
    private List<T> list;


    public PageInfoNew() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfoNew(List<T> list) {
        this(list, 8);
    }

    public PageInfoNew(List<T> list, PageParams page){
        this.pageNum=page.getPageNum();
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.pages = page.getPages();
        this.list = list;
        this.size = page.getSize();
        this.total = page.getPageTotal();
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageInfoNew(List<T> list, int navigatePages) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
            this.total = page.getTotal();
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = 1;
            this.list = list;
            this.size = list.size();
            this.total = list.size();

        }
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
