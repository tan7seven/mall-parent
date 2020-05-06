package com.mall.app.model.vo.product;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class ProductDetailVO extends BaseModel {

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品最低价")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "是否上下架")
    private Boolean putaway;

    @ApiModelProperty(value = "是否可用")
    private Boolean usable;

    @ApiModelProperty(value = "商品状态 1正常 2库存不足")
    private Integer status;

    @ApiModelProperty(value = "商品轮播图")
    private List<String> picList;

    @ApiModelProperty(value = "SKU列表")
    private List<ProductSkuVO> skuList;

    @ApiModelProperty(value = "销售属性列表")
    private List<ProductAttrVO> attrList;

    @ApiModelProperty(value = "显示属性列表")
    private List<ProductAttrVO> showAttrList;

    @ApiModelProperty(value = "商品详情")
    private String detail;
}
