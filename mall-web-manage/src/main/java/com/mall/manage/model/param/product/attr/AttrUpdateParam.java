package com.mall.manage.model.param.product.attr;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class AttrUpdateParam extends BaseParam {
    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID")
    private Long id;
    @NotNull(message = "类目ID不能为空")
    @ApiModelProperty(value = "类目ID")
    private Long typeId;
    @NotNull(message = "属性名不能为空")
    @ApiModelProperty(value = "属性名")
    private String name;
    @ApiModelProperty(value = "是否可用")
    private Boolean usable;
    @ApiModelProperty(value = "是否显示")
    private Boolean showed;

    @ApiModelProperty(value = "可输入数据")
    private String inputData;
    public Boolean getUsable(){
        return null == this.usable ? Boolean.FALSE : this.usable;
    }
    public Boolean getShowed(){
        return null == this.showed ? Boolean.FALSE : this.showed;
    }

}
