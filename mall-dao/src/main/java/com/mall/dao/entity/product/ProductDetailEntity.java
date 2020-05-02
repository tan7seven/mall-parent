package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 *  商品属性名属性值关联表
 */
@Data
@TableName("mall_product_detail")
public class ProductDetailEntity extends BaseEntity {

    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 详情信息
     */
    private String detail;
}
