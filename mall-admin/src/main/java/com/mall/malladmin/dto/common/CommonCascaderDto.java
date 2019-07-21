package com.mall.malladmin.dto.common;

import lombok.Data;

import java.util.List;

/**
 * 联级选择器通用vo
 */
@Data
public class CommonCascaderDto {
    /**
     * 值
     */
    private String value;
    /**
     * 显示的文本
     */
    private String label;
    /**
     * 是否禁用
     */
    private Boolean disabled;
    /**
     * 子选项
     */
    private List<CommonCascaderDto> children;

}
