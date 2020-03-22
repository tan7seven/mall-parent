package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 * 商品属性名
 */
@Data
@TableName("mall_product_property_name")
public class ProductAttrNameEntity extends BaseEntity {
    /**
     * 商品类目ID
     */
    private Long typeId;
    /**
     * 属性名
     */
    private String name;
    /**
     * 	是否可用
     * 	0：否
     * 	1：是
     */
    private Boolean usable;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    private Boolean show;
    /**
     * 	是否删除
     * 	0：正常
     * 	1：已删除
     */
    private Boolean delete;

    /**
     * 类型
     * 1：销售属性
     * 2：非销售属性
     */
    private Integer type;

}
