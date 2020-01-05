package com.mall.malladmin.dto.product;

import com.mall.malladmin.dto.common.CommonDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品库存表
 */
@Data
public class ProductSkuDTO extends CommonDTO implements Serializable {
    /**
     * 编号
     */
    private Integer skuId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 属性值
     */
    private String properties;
    /**
     * 商品类目ID
     */
    private Integer typeId;
    /**
     * 商品类目
     */
    private String typeName;
    /**
     * 	商品价格
     */
    private BigDecimal price;
    /**
     * 	图片地址
     */
    private String picUrl;
    /**
     * 	商品最低价格
     */
    private BigDecimal priceMin;
    /**
     * 	商品成本
     */
    private BigDecimal cost;
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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifyTime;
    /**
     * SKU属性值
     */
    private String propertyValueA;
    private String propertyValueB;
    private String propertyValueC;
    /**
     * 销售属性值A
     */
    private List<ProductPropertyValueDTO> propertyValueAOptions;
    /**
     * 销售属性值B
     */
    private List<ProductPropertyValueDTO> propertyValueBOptions;
    /**
     * 销售属性值C
     */
    private List<ProductPropertyValueDTO> propertyValueCOptions;
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    private String isDelete;
}
