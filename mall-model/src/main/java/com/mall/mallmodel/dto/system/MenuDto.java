package com.mall.mallmodel.dto.system;

import lombok.Data;


/**
 * 系统菜单
 */
@Data
public class MenuDto {
    /**
     * 菜单主键
     */
    private String menuId;
    /**
     * 父级菜单主键
     */
    private String parentId;
    /**
     * 菜单编码
     */
    private String menuCode;
    /**
     * 菜单标题
     */
    private String menuTitle;
    /**
     * 菜单路径
     */
    private String menuUrl;
    /**
     * 图标路径
     */
    private String menuIcon;
    /**
     * 图标路径
     */
    private String isHidden;
    /**
     * 排序
     */
    private Integer sort;
}
