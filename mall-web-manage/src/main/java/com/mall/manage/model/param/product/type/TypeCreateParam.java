package com.mall.manage.model.param.product.type;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class TypeCreateParam extends BaseParam {

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称")
    private String typeName;
    /**
     * 父类目编号
     */
    @NotNull(message = "上级ID不能为空")
    @ApiModelProperty(value = "上级ID")
    private Long parentId;
    /**
     * 	排序
     */
    @ApiModelProperty(value = "序号")
    private Integer sort;
    /**
     * 是否显示在导航栏
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean showed;
    /**
     * 状态
     * 0:正常
     * 1：禁用
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean usable;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    private Integer level;

    public Integer getSort(){
        return null == this.sort ? 999 : this.sort;
    }

    public Boolean getShowed(){
        return null == this.showed ? Boolean.FALSE : this.showed;
    }

    public Boolean getUsable(){
        return null == this.usable ? Boolean.FALSE : this.usable;
    }

    public Integer geyLevel(){
        return 0 == this.parentId ? 1 : 2;
    }
}
