package com.mall.malladmin.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 商品属性名
 */
@Data
@Entity
@Table(name = "mall_product_property_name")
public class ProductPropertyName {
    /**
     * 编号
     */
    @Id
    @Column(name = "property_name_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer propertyNameId;
    /**
     * 商品类目ID
     */
    @Column(name = "type_id")
    private Integer typeId;
    /**
     * 属性名
     */
    @Column(length = 64)
    private String name;
    /**
     * 	商是否销售属性
     * 	0：否
     * 	1：是
     */
    @Column(name = "is_sale", length = 1)
    private String isSale;
}
