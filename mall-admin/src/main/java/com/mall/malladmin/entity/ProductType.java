package com.mall.malladmin.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * 商品类目表
 */
@Data
@Entity
@Table(name = "mall_product_type")
public class ProductType {

    /**
     * 状态：0-正常
     */
    public static final String NORMAL = "0";
    /**
     * 状态：1-禁用
     */
    public static final String FORBIDDEN = "1";
    /**
     * 编号
     */
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer typeId;
    /**
     * 分类名称
     */
    @Column(name = "type_name",length = 64)
    private String typeName;
    /**
     * 父类目编号
     */
    @Column(name = "father_id")
    private Integer fatherId;
    /**
     * 	排序
     */
    @Column()
    private Integer order;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    @Column(length = 1)
    private String status;
}
