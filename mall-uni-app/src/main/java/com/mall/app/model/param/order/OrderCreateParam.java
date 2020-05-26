package com.mall.app.model.param.order;

import com.mall.dao.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel
public class OrderCreateParam extends BaseParam {

    @NotEmpty(message = "商品SKU不能为空")
    @ApiModelProperty(value = "SKU ID 集合")
    private List<Long> skuIdList;
}
