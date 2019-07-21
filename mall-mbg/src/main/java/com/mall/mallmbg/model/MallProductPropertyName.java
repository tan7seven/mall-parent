package com.mall.mallmbg.model;

import java.io.Serializable;

public class MallProductPropertyName implements Serializable {
    /**
     * 	编号
     *
     * @mbggenerated
     */
    private Integer propertyNameId;

    /**
     * 	商品类目ID
     *
     * @mbggenerated
     */
    private Integer typeId;

    /**
     * 属性名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 是否销售属性
     *
     * @mbggenerated
     */
    private String isSale;

    private String isShow;

    private String isUsable;

    private static final long serialVersionUID = 1L;

    public Integer getPropertyNameId() {
        return propertyNameId;
    }

    public void setPropertyNameId(Integer propertyNameId) {
        this.propertyNameId = propertyNameId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propertyNameId=").append(propertyNameId);
        sb.append(", typeId=").append(typeId);
        sb.append(", name=").append(name);
        sb.append(", isSale=").append(isSale);
        sb.append(", isShow=").append(isShow);
        sb.append(", isUsable=").append(isUsable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}