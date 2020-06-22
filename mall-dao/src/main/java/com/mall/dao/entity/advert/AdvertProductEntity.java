package com.mall.dao.entity.advert;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("mall_advert_product")
public class AdvertProductEntity extends BaseEntity {
    /**
     * 广告ID
     */
    private Long advertId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 商品名称
     */
    private String productName;
}
