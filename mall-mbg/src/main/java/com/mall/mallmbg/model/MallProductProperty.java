package com.mall.mallmbg.model;

import java.io.Serializable;

public class MallProductProperty implements Serializable {
    /**
     * 编号
     *
     * @mbggenerated
     */
    private Integer propertyId;

    /**
     * 商品编号
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * 属性名编号
     *
     * @mbggenerated
     */
    private Integer nameId;

    /**
     * 属性值编号
     *
     * @mbggenerated
     */
    private Integer valueId;

    private static final long serialVersionUID = 1L;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propertyId=").append(propertyId);
        sb.append(", productId=").append(productId);
        sb.append(", nameId=").append(nameId);
        sb.append(", valueId=").append(valueId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}