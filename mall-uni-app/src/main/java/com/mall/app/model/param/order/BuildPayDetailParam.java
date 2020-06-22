package com.mall.app.model.param.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/28
 */
@Data
@ApiModel
public class BuildPayDetailParam {

    @NotEmpty(message = "商品不能为空")
    @ApiModelProperty(value = "SKU集合")
    List<OrderSkuParam> skuList;
}
