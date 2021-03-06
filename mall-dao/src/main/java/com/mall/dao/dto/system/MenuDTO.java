package com.mall.dao.dto.system;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.util.List;


/**
 * 系统菜单
 */
@Data
public class MenuDTO extends CommonDTO {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 菜单主键
     */
    private Long menuId;
    /**
     * 父级菜单主键
     */
    private Long parentId;
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
    private Boolean isHidden;
    /**
     * 子级菜单
     */
    private List<MenuDTO> children;
    /**
     * 是否有子级
     */
    private Boolean hasChildren = true;
    /**
     * 按钮列表
     */
    private List<String> buttonList;
}
