package com.mall.malladmin.dto.product;

import com.mall.malladmin.dto.common.CommonDto;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
public class ProductPropertyValueDto extends CommonDto implements Serializable {
    /**
     * 编号
     */
    private Integer propertyValueId;
    /**
     * 属性名编码
     */
    private Integer propertyNameId;
    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 属性值
     */
    private String value;
    /**
     * 是否销售属性
     */
    private String isSale;
    /**
     * 商品编号
     */
    private Integer productId;
}
