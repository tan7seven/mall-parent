package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 * 商品属性名
 */
@Data
@TableName("mall_product_attr_name")
public class ProductAttrNameEntity extends BaseEntity {
    /**
     * 商品类目ID
     */
    private Long typeId;
    /**
     * 属性名
     */
    private String name;
    /**
     * 类型
     * 1：销售属性
     * 2：非销售属性
     */
    private Integer type;
    /**
     * 	是否可用
     * 	0：否
     * 	1：是
     */
    @TableField(value ="is_usable")
    private Boolean usable;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    @TableField(value ="is_showed")
    private Boolean showed;
    /**
     * 	是否删除
     * 	0：正常
     * 	1：已删除
     */
    @TableLogic(value="0",delval="1") // 最好写上，全局配置了，坑我，他找不到，还乱码 // value 默认未删除，deval 删除了
    @TableField(value ="is_deleted")
    private Boolean deleted;
    /**
     * 输入类型
     */
    private Integer inputType;
    /**
     * 可输入数据
     */
    private String inputData;

}
