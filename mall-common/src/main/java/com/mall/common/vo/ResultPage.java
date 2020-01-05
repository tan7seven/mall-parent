package com.mall.common.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页工具类
 */
@Data
public class ResultPage<T> {
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private Integer pageNum;
    private List<T> list;

}
