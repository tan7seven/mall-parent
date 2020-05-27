package com.mall.app.model.vo.product;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel
public class ProductListVO extends BaseModel {
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "商品名称")
    private Long productId;
    @ApiModelProperty(value = "商品最低价")
    private BigDecimal minPrice;
    @ApiModelProperty(value = "图片地址")
    private String picUrl;

}
