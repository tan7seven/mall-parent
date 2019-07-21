package com.mall.mallmbg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MallOrdersReturnApply implements Serializable {
    /**
     * 主键	
     *
     * @mbggenerated
     */
    private String applyId;

    /**
     * 	退货编码
     *
     * @mbggenerated
     */
    private String applyCode;

    /**
     * 订单id
     *
     * @mbggenerated
     */
    private String ordersId;

    /**
     * 订单商品明细编号
     *
     * @mbggenerated
     */
    private String ordersItemsId;

    /**
     * 	用户id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 退款金额
     *
     * @mbggenerated
     */
    private BigDecimal returnPrice;

    /**
     * 	公司收货地址id
     *
     * @mbggenerated
     */
    private String companyAddressId;

    /**
     * 退货原因
     *
     * @mbggenerated
     */
    private String returnReason;

    /**
     * 	图片，以，号隔开
     *
     * @mbggenerated
     */
    private String returnPic;

    /**
     * 退货状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     *
     * @mbggenerated
     */
    private String returnStatus;

    /**
     * 数量
     *
     * @mbggenerated
     */
    private Integer returnAmount;

    /**
     * 申请时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 处理时间
     *
     * @mbggenerated
     */
    private Date handleTime;

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

    private static final long serialVersionUID = 1L;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrdersItemsId() {
        return ordersItemsId;
    }

    public void setOrdersItemsId(String ordersItemsId) {
        this.ordersItemsId = ordersItemsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(BigDecimal returnPrice) {
        this.returnPrice = returnPrice;
    }

    public String getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(String companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnPic() {
        return returnPic;
    }

    public void setReturnPic(String returnPic) {
        this.returnPic = returnPic;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Integer returnAmount) {
        this.returnAmount = returnAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", applyId=").append(applyId);
        sb.append(", applyCode=").append(applyCode);
        sb.append(", ordersId=").append(ordersId);
        sb.append(", ordersItemsId=").append(ordersItemsId);
        sb.append(", userId=").append(userId);
        sb.append(", returnPrice=").append(returnPrice);
        sb.append(", companyAddressId=").append(companyAddressId);
        sb.append(", returnReason=").append(returnReason);
        sb.append(", returnPic=").append(returnPic);
        sb.append(", returnStatus=").append(returnStatus);
        sb.append(", returnAmount=").append(returnAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}