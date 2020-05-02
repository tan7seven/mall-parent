package com.mall.manage.model.vo.product.sku;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/8
 */
@Data
@ApiModel
public class SkuPageVO extends BaseVO {
    /**
     * 编号
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private String attrJson;

    @ApiModelProperty(value = "属性值")
    private String attrValue;
    /**
     * 商品类目ID
     */
    @ApiModelProperty(value = "商品类目ID")
    private Integer typeId;
    /**
     * 	商品价格
     */
    @ApiModelProperty(value = "商品价格")
    private BigDecimal salePrice;
    /**
     * 	图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String picUrl;
    /**
     * 	商品成本
     */
    @ApiModelProperty(value = "商品成本")
    private BigDecimal costPrice;
    /**
     * SKU历史销售总数
     */
    @ApiModelProperty(value = "SKU历史销售总数")
    private Integer sellSum;
    /**
     * 	商品库存
     */
    @ApiModelProperty(value = "商品库存")
    private Integer stock;
}
