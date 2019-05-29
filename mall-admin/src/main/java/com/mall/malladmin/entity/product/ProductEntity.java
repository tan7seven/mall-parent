package com.mall.malladmin.entity.product;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 */
@Data
@Entity
@Table(name = "mall_product")
public class ProductEntity {

    /**
     * 状态：0-上架
     */
    public static final String PUT_AWAY = "0";
    /**
     * 状态：1-下架
     */
    public static final String SOLD_OUT = "1";
    /**
     * 状态：2-已删除
     */
    public static final String DELETED = "2";


    /**
     *商品编号
     */
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    /**
     * 分类编号
     */
    @Column(name = "type_id")
    private Integer typeId;
    /**
     * 商品名称
     */
    @Column(length = 64,name = "product_name")
    private String productName;
    /**
     * 商品主图
     */
    @Column(name = "img_url")
    private String imgUrl;
    /**
     * 商品最低价
     */
    @Column(name = "price_min")
    private BigDecimal priceMin;
    /**
     * 点击量
     */
    @Column()
    private Integer hits;
    /**
     * 商品状态
     * 0：上架
     * 1：下架
     * 2：已删除
     */
    @Column(length = 1)
    private String status;
    /**
     * 新增时间
     */
    @Column(name = "add_time")
    private Date addTime;

}
