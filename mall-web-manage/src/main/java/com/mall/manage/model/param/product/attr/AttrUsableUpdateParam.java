package com.mall.manage.model.param.product.attr;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class AttrUsableUpdateParam extends BaseParam {
    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "是否可用")
    private Boolean usable;

    public Boolean getUsable(){
        return null == this.usable ? Boolean.FALSE : this.usable;
    }

}
