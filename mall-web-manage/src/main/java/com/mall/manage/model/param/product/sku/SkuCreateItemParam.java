package com.mall.manage.model.param.product.sku;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/9
 */
@Data
@ApiModel
public class SkuCreateItemParam extends BaseParam {

    @ApiModelProperty(value = "sku主键")
    private Long id;

    @NotNull(message = "库存不能为空")
    @ApiModelProperty(value = "库存")
    private Integer stock;

    @NotNull(message = "销售价不能为空")
    @ApiModelProperty(value = "销售价")
    private BigDecimal salePrice;

    @NotNull(message = "成本价不能为空")
    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @Valid
    @NotEmpty(message = "属性列表不能为空")
    @ApiModelProperty(value = "属性列表")
    private List<SkuAttrParam> attrValueList;
}
