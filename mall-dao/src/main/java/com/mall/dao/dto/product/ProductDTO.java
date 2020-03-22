package com.mall.dao.dto.product;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品信息
 */
@Data
public class ProductDTO extends CommonDTO implements Serializable {
    /**
     *商品编号
     */
    private Long productId;
    /**
     * 分类编号
     */
    private Long typeId;
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
    private Long productTypeId;
    /**
     * 类目父类ID
     */
    private Long productTypeParentId;
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
    private List<ProductAttrValueDTO> propertyValueAOptions;
    /**
     * 销售属性值B
     */
    private List<ProductAttrValueDTO> propertyValueBOptions;
    /**
     * 销售属性值C
     */
    private List<ProductAttrValueDTO> propertyValueCOptions;
    /**
     * 是否销售属性
     */
    private Integer type;
    /**
     * 是否可用
     */
    private Boolean isUsable;

}
