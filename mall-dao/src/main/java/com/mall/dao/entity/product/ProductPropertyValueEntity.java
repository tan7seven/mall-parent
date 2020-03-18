package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
@TableName("mall_product_property_value")
public class ProductPropertyValueEntity  implements Serializable {

    public static final Integer IS_SALE = 0;
    public static final Integer NOT_SALE = 1;
    /**
     * 编号
     */
    @Id
    private Integer propertyValueId;
    /**
     * 属性名编码
     */
    private Integer propertyNameId;
    /**
     * 属性值
     */
    private String value;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 是否销售属性
     * 0:是
     * 1：否
     */
    private Integer isSale;
}
