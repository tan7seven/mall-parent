package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 */
@Data
@TableName("mall_product_img")
public class ProductImgEntity extends BaseEntity {

    public static final String TYPE_CODE_PRODUCT = "RODUCT";
    public static final String TYPE_CODE_SKU="SKU";
    /**
     *商品图片编号
     */
    private Integer imgId;
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
     * 新增时间
     */
    private Date createTime;
    /**
     * 	外键-商品、SKU信息表
     */
    private String foreignId;
    /**
     * product->商品，sku->SKU
     */
    private String typeCode;
}
