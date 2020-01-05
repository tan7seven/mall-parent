package com.mall.common.Param;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pageNum = 1;
    private Integer pageSize = 10;
}