package com.mall.dao.entity.cart;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/23
 */
@Data
@TableName("mall_cart")
public class CartEntity extends BaseEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * SKU id
     */
    private Long skuId;
    /**
     * 数量
     */
    private Integer amount;
}
