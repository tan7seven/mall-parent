package com.mall.malladmin.dto.system;

import com.mall.malladmin.dto.common.CommonDto;
import lombok.Data;

import java.util.List;


/**
 * 系统菜单
 */
@Data
public class MenuDto extends CommonDto{
    /**
     * 菜单主键
     */
    private String menuId;
    /**
     * 父级菜单主键
     */
    private String parentId;
    private String parentTitle;
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
     * 子级菜单
     */
    private List<MenuDto> children;
    /**
     * 是否有子级
     */
    private Boolean hasChildren = true;

    /**
     * 按钮列表
     */
    private List<String> buttonList;
}
