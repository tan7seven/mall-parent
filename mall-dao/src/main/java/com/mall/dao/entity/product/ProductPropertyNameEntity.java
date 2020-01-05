package com.mall.dao.entity.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品属性名
 */
@Data
@Entity
@Table(name = "mall_product_property_name")
public class ProductPropertyNameEntity  implements Serializable {
    public static final String IS_SALE = "0";
    public static final String NOT_SALE = "1";
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
     * 	是否销售属性
     * 	0：否
     * 	1：是
     */
    @Column(name = "is_sale", length = 1)
    private String isSale;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    @Column(name = "is_show", length = 1)
    private String isShow;
    /**
     * 	是否删除
     * 	0：正常
     * 	1：已删除
     */
    @Column(name = "is_delete", length = 1)
    private String isDelete;

}
