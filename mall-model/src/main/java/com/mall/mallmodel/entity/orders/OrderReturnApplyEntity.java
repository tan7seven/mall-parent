package com.mall.mallmodel.entity.order;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请表
 */
@Data
@Entity
@Table(name = "mall_order_return_apply")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class OrderReturnApplyEntity {
    /**
     * 退货状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     */
    public static final String RETURN_STATUS_WAIT = "0";
    public static final String RETURN_STATUS_RETURNING = "1";
    public static final String RETURN_STATUS_FINISHED = "2";
    public static final String RETURN_STATUS_REFUSE="3";

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(length = 32,name = "apply_id")
    private String applyId;
    /**
     * 订单id
     */
    @Column(length = 32,name = "order_id")
    private String orderId;
    /**
     * 订单商品明细编号
     */
    @Column(name = "order_items_id", length = 32)
    private String orderItemsId;
    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32)
    private String userId;
    /**
     * 申请退款金额
     */
    @Column(name = "return_price")
    private BigDecimal returnPrice;
    /**
     * 实际退款金额
     */
    @Column(name = "real_return_price")
    private BigDecimal realReturnPrice;
    /**
     * 公司收货地址id
     */
    @Column(name = "company_address_id", length = 32)
    private String companyAddressId;
    /**
     *	退货原因
     */
    @Column(name = "return_reason")
    private String returnReason;
    /**
     * 图片，以，号隔开
     */
    @Column(name = "return_pic")
    private String returnPic;
    /**
     * 退货状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     */
    @Column(name = "return_status", length = 1)
    private String returnStatus;
    /**
     * 数量
     */
    @Column(name = "return_amount")
    private Integer returnAmount;
    /**
     * 申请时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 处理时间
     */
    @Column(name = "handle_time")
    private Date handleTime;
    /**
     * 处理备注
     */
    @Column(name = "handle_remark")
    private String handleRemark;
    /**
     * 处理人
     */
    @Column(name = "handle_Person", length = 32)
    private String handlePerson;
    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;
    /**
     * 确认收货人
     */
    @Column(name = "receive_person", length = 32)
    private String receivePerson;
    /**
     * 确认收货备注
     */
    @Column(name = "receive_Remark")
    private String receiveRemark;
    /**
     * 是否删除
     * 0-》否
     * 1-》是
     */
    @Column(name = "is_Delete", length = 1)
    private String isDelete;
}
