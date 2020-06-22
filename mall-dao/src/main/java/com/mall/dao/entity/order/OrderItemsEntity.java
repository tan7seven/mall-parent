package com.mall.dao.entity.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品明细表
 */
@Data
@TableName("mall_order_items")
public class OrderItemsEntity extends BaseEntity {
    /**
     * 订单主键
     */
    private Long orderId;
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 商品SKU编号
     */
    private Long skuId;
    /**
     * 商品数量
     */
    private Integer skuAmount;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal skuPrice;
    /**
     *	商品属性
     */
    private String skuAttr;
    /**
     * 优惠券优惠分解金额
     */
    private BigDecimal couponPrice;
    /**
     * 商品实际销售金额
     */
    private BigDecimal realPrice;
    /**
     * 删除状态：0->未删除；1->已删除
     */
    @TableLogic(value="0",delval="1")
    @TableField(value ="is_deleted")
    private Boolean deleted;
}
