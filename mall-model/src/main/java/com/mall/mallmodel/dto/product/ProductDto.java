package com.mall.mallmodel.dto.product;

import com.mall.mallmodel.dto.common.CommonDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品信息
 */
@Data
public class ProductDto extends CommonDto implements Serializable {
    /**
     *商品编号
     */
    private Integer productId;
    /**
     * 分类编号
     */
    private Integer typeId;
    /**
     * 分类名称
     */
    private String typeName;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品主图
     */
    private String picUrl;
    /**
     * 商品图片集合
     */
    private String[] picUrlArray;
    /**
     * 商品最低价
     */
    private BigDecimal priceMin;
    /**
     * 点击量
     */
    private Integer hits;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 商品状态
     * 0：上架
     * 1：下架
     */
    private String isPutaway;
    /**
     * 新增时间
     */
    private Date addTime;
    /**
     * 描述
     */
    private String detail;
    /**
     * 类目ID
     */
    private Integer productTypeId;
    /**
     * 类目父类ID
     */
    private Integer productTypeParentId;
    /**
     * 类目销售属性
     */
    private String[] productPropertyIsSaleChecked;
    /**
     * 类目非销售属性
     */
    private String[] productPropertyNotSaleChecked;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 销售属性值A
     */
    private List<ProductPropertyValueDto> propertyValueAOptions;
    /**
     * 销售属性值B
     */
    private List<ProductPropertyValueDto> propertyValueBOptions;
    /**
     * 销售属性值C
     */
    private List<ProductPropertyValueDto> propertyValueCOptions;
    /**
     * 是否销售属性
     */
    private String isSale;

}
