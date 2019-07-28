package com.mall.malladmin.entity.orders;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 */
@Data
@Entity
@Table(name = "mall_orders")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OrdersEntity {
    //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->已完成评价；6->无效订单
    public static final String ORDERS_STATUS_OBLIGATION = "0";
    public static final String ORDERS_STATUS_TO_SEND = "1";
    public static final String ORDERS_STATUS_SEND = "2";
    public static final String ORDERS_STATUS_RECEIVE = "3";
    public static final String ORDERS_STATUS_CLOSED = "4";
    public static final String ORDERS_STATUS_FINISHED = "5";
    public static final String ORDERS_STATUS_INVALID = "6";
    //删除状态：0->未删除；1->已删除
    public static final String NOT_DELETE = "0";
    public static final String IS_DELETE = "1";
    /**
     * 订单ID
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32,name = "orders_id")
    private String ordersId;
    /**
     * 用户ID
     */
    @Column(length = 32,name = "user_Id")
    private String userId;
    /**
     * 订单总价格
     */
    @Column(name = "total_Price")
    private BigDecimal totalPrice;
    /**
     * 应付金额（实际支付金额）
     */
    @Column(name = "pay_Price")
    private BigDecimal payPrice;
    /**
     * 运费金额
     */
    @Column(name = "freight_Price")
    private BigDecimal freightPrice;
    /**
     * 促销优化金额（促销价、满减、阶梯价）
     */
    @Column(name = "promotion_Price")
    private BigDecimal promotionPrice;
    /**
     *	积分抵扣金额
     */
    @Column(name = "score_Price")
    private BigDecimal scorePrice;
    /**
     * 优惠券抵扣金额
     */
    @Column(name = "coupon_Price")
    private BigDecimal couponPrice;
    /**
     * 管理员后台调整订单使用的折扣金额
     */
    @Column(name = "discount_Price")
    private BigDecimal discountPrice;
    /**
     * 积分
     */
    @Column(name = "score")
    private Integer score;
    /**
     * 成长值
     */
    @Column(name = "growth")
    private Integer growth;
    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    @Column(length = 1,name = "pay_Type")
    private String payType;
    /**
     * 订单来源：0->PC订单；1->app订单
     */
    @Column(length = 1,name = "source_Type")
    private String sourceType;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成（已收货）；4->已关闭；5->完成评价；6->无效订单
     */
    @Column(length = 1,name = "orders_Status")
    private String ordersStatus;
    /**
     * 物流公司(配送方式)
     */
    @Column(length = 64,name = "delivery_Company")
    private String deliveryCompany;
    /**
     * 物流单号
     */
    @Column(length = 64,name = "delivery_Code")
    private String deliveryCode;
    /**
     * 自动确认时间（天）
     */
    @Column(name = "auto_Confirm_Day")
    private Integer autoConfirmDay;
    /**
     * 收货人姓名
     */
    @Column(length = 64,name = "receiver_Name")
    private String receiverName;
    /**
     * 收货人电话
     */
    @Column(length = 64,name = "receiver_Phone")
    private String receiverPhone;
    /**
     * 收货人邮编
     */
    @Column(length = 64,name = "receiver_Post_Code")
    private String receiverPostCode;
    /**
     * 省份/直辖市
     */
    @Column(length = 64,name = "receiver_Province")
    private String receiverProvince;
    /**
     * 城市
     */
    @Column(length = 64,name = "receiver_City")
    private String receiverCity;
    /**
     * 区
     */
    @Column(length = 64,name = "receiver_Region")
    private String receiverRegion;
    /**
     * 详细地址
     */
    @Column(name = "receiver_Detail_Address")
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    @Column(name = "orders_Remark")
    private String ordersRemark;
    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    @Column(length = 1,name = "is_Confirm")
    private String isConfirm;
    /**
     * 删除状态：0->未删除；1->已删除
     */
    @Column(length = 1,name = "is_Delete")
    private String isDelete;
    /**
     * 下单时使用的积分
     */
    @Column(name = "use_Score")
    private Integer useScore;
    /**
     * 创建时间
     */
    @Column(name = "create_Time")
    private Date createTime;
    /**
     * 支付时间
     */
    @Column(name = "payment_Time")
    private Date paymentTime;
    /**
     * 发货时间
     */
    @Column(name = "delivery_Time")
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    @Column(name = "receive_Time")
    private Date receiveTime;
    /**
     * 修改时间
     */
    @Column(name = "modify_Time")
    private Date modifyTime;
}
