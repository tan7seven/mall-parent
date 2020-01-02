package com.mall.malladmin.entity.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  商品详情介绍
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
//    @GeneratedValue(generator = "uuid2")
    private Integer detailId;

    /**
     * 商品编号
     */
    @Column(name = "product_id")
    private Integer productId;
    /**
     * 详情信息
     */
    @Column()
    private String detail;
}
