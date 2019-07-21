package com.mall.mallmbg.model;

import java.io.Serializable;

public class MallProductDetail implements Serializable {
    /**
     * 商品详情编号
     *
     * @mbggenerated
     */
    private Integer detailId;

    /**
     * 商品ID
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * 详情描述
     *
     * @mbggenerated
     */
    private String detail;

    private static final long serialVersionUID = 1L;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", detailId=").append(detailId);
        sb.append(", productId=").append(productId);
        sb.append(", detail=").append(detail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}