package com.mall.malladmin.entity.product;

import lombok.Data;

import javax.persistence.*;

/**
 *  商品属性名属性值关联表
 */
@Data
@Entity
@Table(name = "mall_product_detail")
public class ProductDetailEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "detail_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer detailId;
    /**
     * 商品编号
     */
    @Column(name = "product_id")
    private Integer productId;
    /**
     * 详情信息
     */
    @Column(length = 2550)
    private String describe;
}
