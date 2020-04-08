package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品库存表
 */
@Data
@TableName("mall_product_sku")
public class ProductSkuEntity extends BaseEntity {
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 属性值
     */
    private String attrJson;
    /**
     * 	商品价格
     */
    private BigDecimal salePrice;
    /**
     * 	图片地址
     */
    private String picUrl;
    /**
     * 	商品成本
     */
    private BigDecimal costPrice;

    private Integer sellSum;
    /**
     * 	商品库存
     */
    private Integer stock;
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    @TableLogic(value="0",delval="1")
    @TableField(value = "is_deleted")
    private Boolean delete;
}
