package com.mall.manage.model.param.product.attr;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class AttrShowedUpdateParam extends BaseParam {
    @NotNull(message = "主键ID不能为空")
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "是否显示")
    private Boolean showed;

    public Boolean getShowed(){
        return null == this.showed ? Boolean.FALSE : this.showed;
    }
}
