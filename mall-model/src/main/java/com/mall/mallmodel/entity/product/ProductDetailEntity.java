package com.mall.mallmodel.entity.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  商品属性名属性值关联表
 */
@Data
@Entity
@Table(name = "mall_product_detail")
public class ProductDetailEntity implements Serializable{
    /**
     * 编号
     */
    @Id
    @Column(name = "detail_id",length = 32)
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator = "jpa-uuid")
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
    private String detail;
}
