package com.mall.common.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class RestPage<T>{
    private Integer page;
    private Integer pageSize;
    private Integer total;
    private List<T> records;

    public RestPage(){
        this.page = 1;
        this.pageSize = 20;
        this.total = 0;
    }
    public RestPage(Integer page, Integer pageSize, Integer total){
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }
    public RestPage(Long page, Long pageSize, Long total){
        this.page = page.intValue();
        this.pageSize = pageSize.intValue();
        this.total = total.intValue();
    }

    public void setTotal(Long total){
        this.total = total.intValue();
    }
}
