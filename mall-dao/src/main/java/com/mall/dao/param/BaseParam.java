package com.mall.dao.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseParam implements Serializable {
    private static final long serialVersionUID = 2302873019826104491L;

    private Integer page;
    private Integer pageSize;

    public Integer getPage(){
        return null == this.page ? 1 :this.page;
    }
    public Integer getPageSize(){
        return null == this.pageSize ? 20 : this.pageSize;
    }
}
