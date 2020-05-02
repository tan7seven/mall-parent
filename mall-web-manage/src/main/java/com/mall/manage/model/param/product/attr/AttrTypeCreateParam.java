package com.mall.manage.model.param.product.attr;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class AttrTypeCreateParam extends BaseParam {

    @ApiModelProperty(value = "主键")
    private Long id;
    @NotBlank(message = "属性名不能为空")
    @ApiModelProperty(value = "属性名")
    private String name;
}
