package com.bycg.conmmon.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author Mirror
 * @CreateDate 2020/3/17.
 * 分页结果集封装类
 */
@Data
public class PageResult<T> {

    private Long totalCount; //总条数
    private Integer totalPage;  //总页数
    private Integer currPage; //当前页
    private List<T> list;       //当前页数据
    private Integer pageSize;   //每页记录数
    private List<Map<String ,Object>> mapList;



    public PageResult() {
    }

    public PageResult(Long totalCount, List<T> list) {
        this.totalCount = totalCount;
        this.list = list;
    }

    public PageResult(Long totalCount, Integer totalPage, List<T> list) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
    }
    public PageResult(Long totalCount, Integer totalPage, List<T> list,Integer currPage, Integer pageSize) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currPage = currPage;
        this.pageSize = pageSize;
    }
    public PageResult(List<Map<String ,Object>> mapList,Long totalCount, Integer totalPage, Integer currPage, Integer pageSize) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.mapList = mapList;
        this.currPage = currPage;
        this.pageSize = pageSize;
    }
}

