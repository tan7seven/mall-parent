package com.mall.mallmbg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MallProductSku implements Serializable {
    /**
     * 编号
     *
     * @mbggenerated
     */
    private Integer skuId;

    /**
     * 商品编号
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * 属性值
     *
     * @mbggenerated
     */
    private String properties;

    /**
     * 图片地址
     *
     * @mbggenerated
     */
    private String picUrl;

    /**
     * 商品价格
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * 历史销售总数
     *
     * @mbggenerated
     */
    private Integer sellSum;

    /**
     * 成本价
     *
     * @mbggenerated
     */
    private Long cost;

    /**
     * 库存
     *
     * @mbggenerated
     */
    private Integer stock;

    /**
     * 新增时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 0：未删除，1：已删除
     *
     * @mbggenerated
     */
    private String isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSellSum() {
        return sellSum;
    }

    public void setSellSum(Integer sellSum) {
        this.sellSum = sellSum;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
        sb.append(", skuId=").append(skuId);
        sb.append(", productId=").append(productId);
        sb.append(", properties=").append(properties);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", price=").append(price);
        sb.append(", sellSum=").append(sellSum);
        sb.append(", cost=").append(cost);
        sb.append(", stock=").append(stock);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}