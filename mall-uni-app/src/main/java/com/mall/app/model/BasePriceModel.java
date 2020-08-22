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
public class BasePriceModel extends BaseModel {
    @ApiModelProperty(value = "商品价格")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal paidPrice;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "运费")
    private BigDecimal freightPrice;
}
