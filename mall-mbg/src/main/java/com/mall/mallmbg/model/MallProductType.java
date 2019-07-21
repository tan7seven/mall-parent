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
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    private Integer level;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        sb.append(", status=").append(status);
        sb.append(", level=").append(level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}