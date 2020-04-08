package com.mall.dao.dto.product;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品库存表
 */
@Data
public class ProductSkuDTO extends CommonDTO{
    /**
     * 编号
     */
    private Long id;
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 属性值
     */
    private String attrJson;
    /**
     * 商品类目ID
     */
    private Integer typeId;
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
    /**
     * SKU历史销售总数
     */
    private Integer sellSum;
    /**
     * 	商品库存
     */
    private Integer stock;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;
}
