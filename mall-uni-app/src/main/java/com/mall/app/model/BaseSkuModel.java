package com.mall.app.model;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/27
 */
@Data
@ApiModel
public class BaseSkuModel extends BaseModel {
    @ApiModelProperty(value = "售价")
    private BigDecimal salePrice;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "SKU ID")
    private Long skuId;
}