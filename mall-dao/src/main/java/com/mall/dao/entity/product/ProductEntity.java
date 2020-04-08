package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息（代码冲突测试github）
 */
@Data
@TableName("mall_product")
public class ProductEntity extends BaseEntity {
    /**
     * 分类编号
     */
    private Long typeId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品主图
     */
    private String picUrl;
    /**
     * 商品最低价
     */
    private BigDecimal minPrice;
    /**
     * 点击量
     */
    private Integer hits;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 是否上下架
     */
    @TableField(value = "is_putaway")
    private Boolean putaway;
    /**
     * 是否可用
     */
    @TableField(value = "is_usable")
    private Boolean usable;
    /**
     * 是否删除
     */
    @TableLogic(value="0",delval="1")
    @TableField(value = "is_deleted")
    private Boolean deleted;
}
