package com.mall.dao.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片表
 */
@Data
@TableName("mall_product_img")
public class ProductImgEntity  implements Serializable {

    public static final String TYPE_CODE_PRODUCT = "RODUCT";
    public static final String TYPE_CODE_SKU="SKU";
    /**
     *商品图片编号
     */
    @Id
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
