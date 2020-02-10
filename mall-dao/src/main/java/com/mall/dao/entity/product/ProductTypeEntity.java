package com.mall.dao.entity.product;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 商品类目表
 */
@Data
@Entity
@Table(name = "mall_product_type")
public class ProductTypeEntity  implements Serializable {

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
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 等级
     */
    @Column
    private Integer level;
    /**
     * 	排序
     */
    @Column()
    private Integer sort;
    /**
     * 是否显示在导航栏
     */
    @Column(name = "is_navigation_bar", length = 1)
    private Integer isNavigationBar;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    @Column(length = 1, name = "is_usable")
    private Integer isUsable;
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    @Column(length = 1, name = "is_delete")
    private Integer isDelete;
}
