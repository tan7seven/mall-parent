package com.mall.mallmodel.entity.orders;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品明细表
 */
@Data
@Entity
@Table(name = "mall_orders_items")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OrdersItemsEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32,name = "items_id")
    private String itemsId;
    /**
     * 订单主键
     */
    @Column(length = 32,name = "orders_id")
    private String ordersId;
    /**
     * 商品编号
     */
    @Column(name = "product_id")
    private Integer productId;
    /**
     * 图片地址
     */
    @Column(name = "pic_url")
    private String picUrl;
    /**
     * 商品SKU编号
     */
    @Column(name = "product_sku_id")
    private Integer productSkuId;
    /**
     * 商品数量
     */
    @Column(name = "product_amount")
    private Integer productAmount;
    /**
     * 商品名称
     */
    @Column(name = "product_name",length = 128)
    private String productName;
    /**
     * 商品价格
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;
    /**
     *	商品属性
     */
    @Column(name = "product_Property")
    private String productProperty;
    /**
     * 商品促销名称
     */
    @Column(name = "promotion_name", length = 128)
    private String promotionName;
    /**
     * 商品促销价格
     */
    @Column(name = "promotion_price")
    private BigDecimal promotionPrice;
    /**
     * 优惠券优惠分解金额
     */
    @Column(name = "coupon_price")
    private BigDecimal couponPrice;
    /**
     * 积分优惠分解金额
     */
    @Column(name = "score_price")
    private BigDecimal scorePrice;
    /**
     * 商品实际销售金额
     */
    @Column(name = "real_price")
    private BigDecimal realPrice;
    /**
     * 可以获得的积分
     */
    @Column()
    private Integer score;
    /**
     * 可以获得的成长值
     */
    @Column()
    private Integer growth;
    /**
     * 评价时间
     */
    @Column(name = "comment_time")
    private Date commentTime;
    /**
     * 评价内容
     */
    @Column(name = "comment_detail")
    private String commentDetail;
    /**
     * 评价图片
     */
    @Column(name = "comment_pic")
    private String commentPic;

}
