package com.mall.manage.model.vo.product.sku;

import com.mall.manage.model.vo.product.BaseVO;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/9
 */
@Data
@ApiModel
public class SkuListVO extends BaseVO {
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
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private String attrJson;
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
     * 	商品库存
     */
    @ApiModelProperty(value = "商品库存")
    private Integer stock;

    @ApiModelProperty(value = "属性值列表")
    private List<AttrValueVO> attrValueList;
}
