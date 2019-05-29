package com.mall.malladmin.vo.product;

import com.mall.malladmin.vo.common.CommonVo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品库存表
 */
@Data
public class ProductSkuVo extends CommonVo implements Serializable {
    /**
     * 编号
     */
    private Integer skuId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 属性值
     */
    private String properties;
    /**
     * 	商品价格
     */
    private BigDecimal price;
    /**
     * 	商品成本
     */
    private BigDecimal cost;
    /**
     * 	商品库存
     */
    private Integer stock;
    /**
     * 新增时间
     */
    private Date addTime = new Date();
    /**
     * 更新时间
     */
    private Date updateTime = new Date();
}
