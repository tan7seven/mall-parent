package com.mall.malladmin.vo.product;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
public class ProductPropertyNameVo implements Serializable {
    /**
     * 编号
     */
    private Integer propertyNameId;
    /**
     * 商品类目ID
     */
    private Integer typeId;
    /**
     * 属性名
     */
    private String name;
    /**
     * 	商是否销售属性
     * 	0：否
     * 	1：是
     */
    private String isSale;
}
