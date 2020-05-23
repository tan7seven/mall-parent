package com.mall.app.model.param.cart;

import com.mall.dao.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/23
 */
@Data
@ApiModel
public class CartAddParam extends BaseParam {
    @NotNull(message = "sku不能为空")
    @ApiModelProperty(value = "skuid")
    private Long skuId;
    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量")
    private Integer amount;
}
