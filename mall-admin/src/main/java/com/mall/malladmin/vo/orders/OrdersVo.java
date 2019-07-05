package com.mall.malladmin.vo.orders;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 */
@Data
public class OrdersVo {
    /**
     * 订单ID
     */
    private String ordersId;
    /**
     * 订单编号
     */
    private String ordersCode;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 订单总价格
     */
    private BigDecimal totalPrice;
    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal payPrice;
    /**
     * 运费金额
     */
    private BigDecimal freightPrice;
    /**
     * 促销优化金额（促销价、满减、阶梯价）
     */
    private BigDecimal promotionPrice;
    /**
     *	积分抵扣金额
     */
    private BigDecimal integrationPrice;
    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponPrice;
    /**
     * 管理员后台调整订单使用的折扣金额
     */
    private BigDecimal discountPrice;
    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    private String payType;
    /**
     * 订单来源：0->PC订单；1->app订单
     */
    private String sourceType;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private String ordersStatus;
    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;
    /**
     * 物流单号
     */
    private String deliveryCode;
    /**
     * 自动确认时间（天）
     */
    private Integer autoConfirmDay;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货人邮编
     */
    private String receiverPostCode;
    /**
     * 省份/直辖市
     */
    private String receiverProvince;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 区
     */
    private String receiverRegion;
    /**
     * 详细地址
     */
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    private String ordersRemark;
    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    private String isConfirm;
    /**
     * 删除状态：0->未删除；1->已删除
     */
    private String isDelete;
    /**
     * 下单时使用的积分
     */
    private Integer useIntegration;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 支付时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    private Date receiveTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
}
