package com.mall.malladmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品库存表
 */
@Data
@Entity
@Table(name = "mall_product_sku")
public class ProductSku {
    /**
     * 编号
     */
    @Id
    @Column(name = "sku_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer skuId;
    /**
     * 商品编号
     */
    @Column(name = "product_id")
    private Integer productId;
    /**
     * 属性值
     */
    @Column(length = 2550)
    private String properties;
    /**
     * 	商品价格
     */
    @Column()
    private BigDecimal price;
    /**
     * 	商品成本
     */
    @Column()
    private BigDecimal cost;
    /**
     * 	商品库存
     */
    @Column()
    private Integer stock;
    /**
     * 新增时间
     */
    @Column(name = "add_time")
    private Date addTime = new Date();
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime = new Date();
}
