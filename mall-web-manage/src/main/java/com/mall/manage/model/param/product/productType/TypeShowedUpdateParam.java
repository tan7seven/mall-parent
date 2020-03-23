package com.mall.manage.model.param.product.productType;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class TypeShowedUpdateParam extends BaseParam {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "是否显示")
    private Boolean showed;
}
