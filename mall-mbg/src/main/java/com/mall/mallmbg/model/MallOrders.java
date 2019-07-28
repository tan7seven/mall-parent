package com.mall.mallmbg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MallOrders implements Serializable {
    /**
     * 	订单id
     *
     * @mbggenerated
     */
    private String ordersId;

    /**
     * 用户id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 订单总金额
     *
     * @mbggenerated
     */
    private BigDecimal totalPrice0;

    /**
     * 应付金额（实际支付金额）
     *
     * @mbggenerated
     */
    private BigDecimal payPrice;

    /**
     * 运费金额
     *
     * @mbggenerated
     */
    private BigDecimal freightPrice;

    /**
     * 促销优化金额（促销价、满减、阶梯价）
     *
     * @mbggenerated
     */
    private BigDecimal promotionPrice;

    /**
     * 积分抵扣金额
     *
     * @mbggenerated
     */
    private BigDecimal scorePrice;

    /**
     * 优惠券抵扣金额
     *
     * @mbggenerated
     */
    private BigDecimal couponPrice;

    /**
     * 管理员后台调整订单使用的折扣金额
     *
     * @mbggenerated
     */
    private BigDecimal discountPrice;

    /**
     * 成长值
     *
     * @mbggenerated
     */
    private Integer growth;

    /**
     * 订单积分
     *
     * @mbggenerated
     */
    private Integer score;

    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     *
     * @mbggenerated
     */
    private String payType;

    /**
     * 订单来源：0->PC订单；1->app订单
     *
     * @mbggenerated
     */
    private String sourceType;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成（已收货）；4->已关闭；5->完成评价；6->无效订单
     *
     * @mbggenerated
     */
    private String ordersStatus;

    /**
     * 物流公司(配送方式)
     *
     * @mbggenerated
     */
    private String deliveryCompany;

    /**
     * 物流单号
     *
     * @mbggenerated
     */
    private String deliveryCode;

    /**
     * 自动确认时间（天）
     *
     * @mbggenerated
     */
    private Integer autoConfirmDay;

    /**
     * 收货人姓名
     *
     * @mbggenerated
     */
    private String receiverName;

    /**
     * 收货人电话
     *
     * @mbggenerated
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     *
     * @mbggenerated
     */
    private String receiverPostCode;

    /**
     * 省份/直辖市
     *
     * @mbggenerated
     */
    private String receiverProvince;

    /**
     * 城市
     *
     * @mbggenerated
     */
    private String receiverCity;

    /**
     * 	区
     *
     * @mbggenerated
     */
    private String receiverRegion;

    /**
     * 	详细地址
     *
     * @mbggenerated
     */
    private String receiverDetailAddress;

    /**
     * 	订单备注
     *
     * @mbggenerated
     */
    private String ordersRemark;

    /**
     * 确认收货状态：0->未确认；1->已确认
     *
     * @mbggenerated
     */
    private String isConfirm;

    /**
     * 删除状态：0->未删除；1->已删除	
     *
     * @mbggenerated
     */
    private String isDelete;

    /**
     * 下单时使用的积分
     *
     * @mbggenerated
     */
    private Integer useScore;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 支付时间
     *
     * @mbggenerated
     */
    private Date paymentTime;

    /**
     * 发货时间
     *
     * @mbggenerated
     */
    private Date deliveryTime;

    /**
     * 确认收货时间
     *
     * @mbggenerated
     */
    private Date receiveTime;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    private BigDecimal totalPrice;

    private static final long serialVersionUID = 1L;

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice0() {
        return totalPrice0;
    }

    public void setTotalPrice0(BigDecimal totalPrice0) {
        this.totalPrice0 = totalPrice0;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getScorePrice() {
        return scorePrice;
    }

    public void setScorePrice(BigDecimal scorePrice) {
        this.scorePrice = scorePrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Integer getAutoConfirmDay() {
        return autoConfirmDay;
    }

    public void setAutoConfirmDay(Integer autoConfirmDay) {
        this.autoConfirmDay = autoConfirmDay;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getOrdersRemark() {
        return ordersRemark;
    }

    public void setOrdersRemark(String ordersRemark) {
        this.ordersRemark = ordersRemark;
    }

    public String getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getUseScore() {
        return useScore;
    }

    public void setUseScore(Integer useScore) {
        this.useScore = useScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ordersId=").append(ordersId);
        sb.append(", userId=").append(userId);
        sb.append(", totalPrice0=").append(totalPrice0);
        sb.append(", payPrice=").append(payPrice);
        sb.append(", freightPrice=").append(freightPrice);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", scorePrice=").append(scorePrice);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", discountPrice=").append(discountPrice);
        sb.append(", growth=").append(growth);
        sb.append(", score=").append(score);
        sb.append(", payType=").append(payType);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", ordersStatus=").append(ordersStatus);
        sb.append(", deliveryCompany=").append(deliveryCompany);
        sb.append(", deliveryCode=").append(deliveryCode);
        sb.append(", autoConfirmDay=").append(autoConfirmDay);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverPhone=").append(receiverPhone);
        sb.append(", receiverPostCode=").append(receiverPostCode);
        sb.append(", receiverProvince=").append(receiverProvince);
        sb.append(", receiverCity=").append(receiverCity);
        sb.append(", receiverRegion=").append(receiverRegion);
        sb.append(", receiverDetailAddress=").append(receiverDetailAddress);
        sb.append(", ordersRemark=").append(ordersRemark);
        sb.append(", isConfirm=").append(isConfirm);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", useScore=").append(useScore);
        sb.append(", createTime=").append(createTime);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}