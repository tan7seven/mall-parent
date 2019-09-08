package com.mall.mallmbg.model;

import java.io.Serializable;

public class MallProductType implements Serializable {
    /**
     * 商品分类编号
     *
     * @mbggenerated
     */
    private Integer typeId;

    /**
     * 分类名称
     *
     * @mbggenerated
     */
    private String typeName;

    /**
     * 父分类编号
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * 是否在导航栏显示
     *
     * @mbggenerated
     */
    private String isNavigationBar;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 0：正常，1：禁用
     *
     * @mbggenerated
     */
    private String isUsable;

    private Integer level;

    /**
     * 0：正常，1：已删除
     *
     * @mbggenerated
     */
    private String isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIsNavigationBar() {
        return isNavigationBar;
    }

    public void setIsNavigationBar(String isNavigationBar) {
        this.isNavigationBar = isNavigationBar;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", typeId=").append(typeId);
        sb.append(", typeName=").append(typeName);
        sb.append(", parentId=").append(parentId);
        sb.append(", isNavigationBar=").append(isNavigationBar);
        sb.append(", sort=").append(sort);
        sb.append(", isUsable=").append(isUsable);
        sb.append(", level=").append(level);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}