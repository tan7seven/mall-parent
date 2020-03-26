package com.mall.manage.model.vo.product.product;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/3/25
 */
@Data
@ApiModel
public class ProductPageVO extends BaseVO {
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;
    /**
     * 分类编号
     */
    @ApiModelProperty(value = "分类编号")
    private Long typeId;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String typeName;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 商品主图
     */
    @ApiModelProperty(value = "商品主图")
    private String picUrl;
    /**
     * 商品最低价
     */
    @ApiModelProperty(value = "商品最低价")
    private BigDecimal minPrice;
    /**
     * 点击量
     */
    @ApiModelProperty(value = "点击量")
    private Integer hits;
    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位")
    private String unit;
    /**
     * 商品状态
     * 0：上架
     * 1：下架
     */
    @ApiModelProperty(value = "上下架状态")
    private Boolean putaway;
    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean usable;
}
