package com.mall.malladmin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性值mapper映射
 */
@Data
public class ProductPropertyDto implements Serializable{

    /**
     * 商品销售属性值的ID信息，如：111（属性名ID）:123（属性值ID）
     */
    private String isSalePropertyId;
    /**
     * 商品销售属性值的String信息，如：尺寸（属性名name）：4.5寸（属性值value）
     */
    private String isSalePropertyString;
    /**
     * 商品非销售属性值的ID信息，如：111（属性名ID）:123（属性值ID）
     */
    private String notSalePropertyId;
    /**
     * 商品非销售属性值的String信息，如：尺寸（属性名name）：4.5寸（属性值value）
     */
    private String notSalePropertyString;
}
