package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 * 商品属性名
 */
@Data
@TableName("mall_product_attr_value")
public class ProductAttrValueEntity extends BaseEntity {
    /**
     * 属性名编码
     */
    private Long nameId;
    /**
     * 属性值
     */
    private String value;
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 类型
     * 1：销售属性
     * 2：显示参数
     */
    private Integer type;
}
