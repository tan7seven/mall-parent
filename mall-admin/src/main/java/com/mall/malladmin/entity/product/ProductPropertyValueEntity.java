package com.mall.malladmin.entity.product;

import lombok.Data;

import javax.persistence.*;

/**
 * 商品属性名
 */
@Data
@Entity
@Table(name = "mall_product_property_value")
public class ProductPropertyValueEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "property_value_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyValueId;
    /**
     * 属性名编码
     */
    @Column(name = "property_name_id")
    private Integer propertyNameId;
    /**
     * 属性值
     */
    @Column()
    private String value;
}
