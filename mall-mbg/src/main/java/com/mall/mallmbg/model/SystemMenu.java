package com.mall.mallmbg.model;

import java.io.Serializable;

public class SystemMenu implements Serializable {
    /**
     * 菜单主键
     *
     * @mbggenerated
     */
    private String menuId;

    /**
     * 父级菜单编码
     *
     * @mbggenerated
     */
    private String parentId;

    /**
     * 菜单编码(权限管控)
     *
     * @mbggenerated
     */
    private String menuCode;

    /**
     * 菜单标题
     *
     * @mbggenerated
     */
    private String menuTitle;

    /**
     * 菜单路径
     *
     * @mbggenerated
     */
    private String menuUrl;

    /**
     * 菜单图标
     *
     * @mbggenerated
     */
    private String menuIcon;

    /**
     * 是否隐藏
     *
     * @mbggenerated
     */
    private String isHidden;

    private static final long serialVersionUID = 1L;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", parentId=").append(parentId);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", menuTitle=").append(menuTitle);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", isHidden=").append(isHidden);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}