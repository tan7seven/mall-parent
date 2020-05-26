package com.mall.app.model.vo.cart;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel
public class CartListVO extends BaseModel {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "skuID")
    private Long skuId;
    @ApiModelProperty(value = "图片")
    private String image;
    @ApiModelProperty(value = "属性值")
    private String attrVal;
    @ApiModelProperty(value = "库存")
    private Integer stock;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "数量")
    private Integer number;
}
