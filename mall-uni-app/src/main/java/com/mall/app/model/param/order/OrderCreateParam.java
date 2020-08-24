package com.mall.app.model.param.order;

import com.mall.common.model.BaseModel;
import com.mall.dao.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel
public class OrderCreateParam extends BaseParam {

    @Valid
    @NotEmpty(message = "商品不能为空")
    @ApiModelProperty(value = "SKU ID 集合")
    private List<OrderSkuParam> skuList;

    @ApiModelProperty(value = "备注")
    private String remark;

    @NotBlank(message = "收货地址不能为空")
    @ApiModelProperty(value = "收货地址ID")
    private Long addressId;
}
