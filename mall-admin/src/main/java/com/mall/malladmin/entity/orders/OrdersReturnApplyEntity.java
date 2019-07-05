package com.mall.malladmin.entity.orders;

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
@Table(name = "mall_orders_return_apply")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OrdersReturnApplyEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32,name = "apply_id")
    private String applyId;
    /**
     * 退货编码
     */
    @Column(length = 64,name = "apply_code")
    private String applyCode;
    /**
     * 订单id
     */
    @Column(length = 32,name = "orders_id")
    private String ordersId;
    /**
     * 订单商品明细编号
     */
    @Column(name = "orders_items_id", length = 32)
    private String ordersItemsId;
    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32)
    private String userId;
    /**
     * 退款金额
     */
    @Column(name = "return_price")
    private BigDecimal returnPrice;
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
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

}
