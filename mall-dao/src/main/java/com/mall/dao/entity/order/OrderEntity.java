package com.mall.dao.entity.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 */
@Data
@TableName("mall_order")
public class OrderEntity extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 订单号 APP显示单号 交易单号 snowflake生成
     */
    private Long orderCode;
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
    private Integer payType;
    /**
     * @see com.mall.common.enums.OrderStatusEnum
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;
    /**
     * 物流单号
     */
    private String deliveryCode;

    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
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
    private String orderRemark;
    /**
     * 删除状态：0->未删除；1->已删除
     */
    @TableLogic(value="0",delval="1")
    @TableField(value ="is_deleted")
    private Boolean deleted;
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
}
