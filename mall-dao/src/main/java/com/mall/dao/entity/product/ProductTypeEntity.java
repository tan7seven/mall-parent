package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    @TableField(value ="is_showed")
    private Boolean showed;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    @TableField(value ="is_usable")
    private Boolean usable;
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    @TableLogic(value="0",delval="1") // 最好写上，全局配置了，坑我，他找不到，还乱码 // value 默认未删除，deval 删除了
    @TableField(value ="is_deleted")
    private Boolean deleted;
}
