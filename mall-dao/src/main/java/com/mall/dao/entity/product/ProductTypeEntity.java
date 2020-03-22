package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;


/**
 * 商品类目表
 */
@Data
@TableName("mall_product_type")
public class ProductTypeEntity extends BaseEntity {

    /**
     * 分类名称
     */
    private String typeName;
    /**
     * 父类目编号
     */
    private Long parentId;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 	排序
     */
    private Integer sort;
    /**
     * 是否显示在导航栏
     */
    private Boolean show;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    private Boolean usable;
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    private Boolean delete;
}
