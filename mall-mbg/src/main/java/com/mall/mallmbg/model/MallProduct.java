package com.mall.mallmbg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MallProduct implements Serializable {
    /**
     * 商品编号
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * 分类编号
     *
     * @mbggenerated
     */
    private Integer typeId;

    /**
     * 商品名称
     *
     * @mbggenerated
     */
    private String productName;

    /**
     * 商品图片列表
     *
     * @mbggenerated
     */
    private String picListUrl;

    /**
     * 商品主图
     *
     * @mbggenerated
     */
    private String picUrl;

    /**
     * 商品最低价
     *
     * @mbggenerated
     */
    private BigDecimal priceMin;

    private Integer sort;

    private String unit;

    /**
     * 点击量
     *
     * @mbggenerated
     */
    private Integer hits;

    /**
     * 是否上架
     *
     * @mbggenerated
     */
    private String isPutaway;

    /**
     * 新增时间
     *
     * @mbggenerated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPicListUrl() {
        return picListUrl;
    }

    public void setPicListUrl(String picListUrl) {
        this.picListUrl = picListUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(String isPutaway) {
        this.isPutaway = isPutaway;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", typeId=").append(typeId);
        sb.append(", productName=").append(productName);
        sb.append(", picListUrl=").append(picListUrl);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", priceMin=").append(priceMin);
        sb.append(", sort=").append(sort);
        sb.append(", unit=").append(unit);
        sb.append(", hits=").append(hits);
        sb.append(", isPutaway=").append(isPutaway);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}