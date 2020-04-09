package com.mall.manage.model.vo.product.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/9
 */
@Data
@ApiModel
public class SkuCreateParam {
    @Valid
    @NotEmpty(message = "属性列表不能为空")
    private List<SkuCreateItemParam> param;

    @NotNull(message = "商品ID不能为空")
    @ApiModelProperty(value = "商品ID")
    private Long productId;
}
