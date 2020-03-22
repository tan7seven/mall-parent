package com.mall.manage.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BaseVO implements Serializable {
    private static final long serialVersionUID = 2302873019826104491L;

    @ApiModelProperty(value = "主键ID")
    private Long id;
}
