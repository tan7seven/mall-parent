package com.mall.mallmbg.model;

import java.io.Serializable;

public class MallProductPropertyValue implements Serializable {
    /**
     * 编号
     *
     * @mbggenerated
     */
    private Integer propertyValueId;

    private Integer productId;

    private String isSale;

    /**
     * 商品属性名编号
     *
     * @mbggenerated
     */
    private Integer propertyNameId;

    /**
     * 属性值
     *
     * @mbggenerated
     */
    private String value;

    private static final long serialVersionUID = 1L;

    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public Integer getPropertyNameId() {
        return propertyNameId;
    }

    public void setPropertyNameId(Integer propertyNameId) {
        this.propertyNameId = propertyNameId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propertyValueId=").append(propertyValueId);
        sb.append(", productId=").append(productId);
        sb.append(", isSale=").append(isSale);
        sb.append(", propertyNameId=").append(propertyNameId);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}