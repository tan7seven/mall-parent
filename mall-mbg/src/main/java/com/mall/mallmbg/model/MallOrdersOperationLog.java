package com.mall.mallmbg.model;

import java.io.Serializable;
import java.util.Date;

public class MallOrdersOperationLog implements Serializable {
    /**
     * 键
     *
     * @mbggenerated
     */
    private String operationId;

    /**
     * 订单编号
     *
     * @mbggenerated
     */
    private String ordersId;

    /**
     * 操作人ID
     *
     * @mbggenerated
     */
    private String operationPerson;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     *
     * @mbggenerated
     */
    private String ordersStatus;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 操作时间
     *
     * @mbggenerated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson;
    }

    public String getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        sb.append(", operationId=").append(operationId);
        sb.append(", ordersId=").append(ordersId);
        sb.append(", operationPerson=").append(operationPerson);
        sb.append(", ordersStatus=").append(ordersStatus);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}