package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

/**
 * 商品图片表
 */
@Data
@TableName("mall_product_img")
public class ProductImgEntity extends BaseEntity {

    public static final String TYPE_CODE_PRODUCT = "PRODUCT";
    public static final String TYPE_CODE_SKU="SKU";
    /**
     * 图片路径
     */
    private String imgUrl;
    /**
     * 图片名
     */
    private String name;
    /**
     * 	图片大小
     */
    private Integer size;
    /**
     * 	外键-商品、SKU信息表
     */
    private Long foreignId;
    /**
  * product->商品，sku->SKU
     */
    private String typeCode;
}
