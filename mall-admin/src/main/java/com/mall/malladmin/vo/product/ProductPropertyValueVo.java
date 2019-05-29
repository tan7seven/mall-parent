package com.mall.malladmin.vo.product;

import com.mall.malladmin.vo.common.CommonVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
public class ProductPropertyValueVo extends CommonVo implements Serializable {
    /**
     * 编号
     */
    private Integer propertyValueId;
    /**
     * 属性名编码
     */
    private Integer propertyNameId;
    /**
     * 属性值
     */
    private String value;
}
