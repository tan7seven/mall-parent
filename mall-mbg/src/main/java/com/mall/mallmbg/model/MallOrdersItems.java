package com.mall.mallmbg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MallOrdersItems implements Serializable {
    /**
     * 	主键
     *
     * @mbggenerated
     */
    private String itemsId;

    /**
     * 订单编号
     *
     * @mbggenerated
     */
    private String ordersId;

    /**
     * 	商品编号
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * 	商品SKU编号
     *
     * @mbggenerated
     */
    private Integer productSkuId;

    /**
     * 	商品数量
     *
     * @mbggenerated
     */
    private Integer productAmount;

    /**
     * 	商品名称
     *
     * @mbggenerated
     */
    private String productName;

    /**
     * 商品价格
     *
     * @mbggenerated
     */
    private BigDecimal productPrice;

    /**
     * 商品属性
     *
     * @mbggenerated
     */
    private String productProperty;

    /**
     * 商品促销名称
     *
     * @mbggenerated
     */
    private String promotionName;

    /**
     * 商品促销价格
     *
     * @mbggenerated
     */
    private BigDecimal promptionPrice;

    /**
     * 优惠券优惠分解金额	
     *
     * @mbggenerated
     */
    private BigDecimal couponPrice;

    /**
     * 积分优惠分解金额	
     *
     * @mbggenerated
     */
    private BigDecimal scorePrice;

    /**
     * 商品实际销售金额
     *
     * @mbggenerated
     */
    private BigDecimal realPrice;

    /**
     * 可以获得的积分
     *
     * @mbggenerated
     */
    private Integer score;

    /**
     * 可以活动的成长值
     *
     * @mbggenerated
     */
    private Integer growth;

    private Date commentTime;

    private String commentDetail;

    private String commentPic;

    private BigDecimal promotionPrice;

    private static final long serialVersionUID = 1L;

    public String getItemsId() {
        return itemsId;
    }

    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Integer productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductProperty() {
        return productProperty;
    }

    public void setProductProperty(String productProperty) {
        this.productProperty = productProperty;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public BigDecimal getPromptionPrice() {
        return promptionPrice;
    }

    public void setPromptionPrice(BigDecimal promptionPrice) {
        this.promptionPrice = promptionPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getScorePrice() {
        return scorePrice;
    }

    public void setScorePrice(BigDecimal scorePrice) {
        this.scorePrice = scorePrice;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public String getCommentPic() {
        return commentPic;
    }

    public void setCommentPic(String commentPic) {
        this.commentPic = commentPic;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemsId=").append(itemsId);
        sb.append(", ordersId=").append(ordersId);
        sb.append(", productId=").append(productId);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", productName=").append(productName);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productProperty=").append(productProperty);
        sb.append(", promotionName=").append(promotionName);
        sb.append(", promptionPrice=").append(promptionPrice);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", scorePrice=").append(scorePrice);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", score=").append(score);
        sb.append(", growth=").append(growth);
        sb.append(", commentTime=").append(commentTime);
        sb.append(", commentDetail=").append(commentDetail);
        sb.append(", commentPic=").append(commentPic);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}