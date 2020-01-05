package com.mall.malladmin.dto.common;

import lombok.Data;

import java.util.List;

/**
 * 树型
 */
@Data
public class TreeDTO {
    /**
     * 节点ID
     */
    private String id;
    /**
     * 节点标签
     */
    private String label;
    /**
     * 是否禁用
     */
    private Boolean disabled;
    /**
     * 子节点
     */
    private List<TreeDTO> children;
}
