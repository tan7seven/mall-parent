package com.mall.malladmin.entity.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品库存表
 */
@Data
@Entity
@Table(name = "mall_product_sku")
public class ProductSkuEntity  implements Serializable {

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
     * 	图片地址
     */
    @Column(name = "pic_url")
    private String picUrl;
    /**
     * 	商品成本
     */
    @Column()
    private BigDecimal cost;

    @Column(length = 1, name = "sell_sum")
    private Integer sellSum;
    /**
     * 	商品库存
     */
    @Column()
    private Integer stock;
    /**
     * 新增时间
     */
    @Column(name = "create_time")
    private Date createTime = new Date();
    /**
     * 更新时间
     */
    @Column(name = "modify_time")
    private Date modifyTime = new Date();
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    @Column(length = 1, name = "is_delete")
    private String isDelete;
}
