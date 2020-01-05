package com.mall.mallmodel.dto.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品明细表
 */
@Data
public class OrderItemsDto {
    /**
     * 主键
     */
    private String itemsId;
    /**
     * 订单主键
     */
    private String orderId;
    /**
     * 类目编号
     */
    private Integer typeId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 商品SKU编号
     */
    private Integer productSkuId;
    /**
     * 商品数量
     */
    private Integer productAmount;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     *	商品属性
     */
    private String productProperty;

    /**
     * 商品促销名称
     */
    private String promotionName;
    /**
     * 商品促销价格
     */
    private BigDecimal promotionPrice;
    /**
     * 优惠券优惠分解金额
     */
    private BigDecimal couponPrice;
    /**
     * 积分优惠分解金额
     */
    private BigDecimal scorePrice;
    /**
     * 商品实际销售金额
     */
    private BigDecimal realPrice;
    /**
     * 可以获得的积分
     */
    private Integer score;
    /**
     * 可以获得的成长值
     */
    private Integer growth;
    /**
     * 评价时间
     */
    private Date commentTime;
    /**
     * 评价内容
     */
    private String commentDetail;
    /**
     * 评价图片
     */
    private String commentPic;

}
