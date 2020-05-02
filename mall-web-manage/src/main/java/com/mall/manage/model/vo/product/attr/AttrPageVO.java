package com.mall.manage.model.vo.product.attr;

import com.mall.common.enums.AttrNameInputTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class AttrPageVO implements Serializable {

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;
    /**
     * 商品类目ID
     */
    @ApiModelProperty(value = "类目ID")
    private Long typeId;
    /**
     * 属性名
     */
    @ApiModelProperty(value = "属性名")
    private String name;
    /**
     * 	商是否销售属性
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 	是否显示
     * 	0：是
     * 	1：否
     */
    @ApiModelProperty(value = "是否显示")
    private Boolean showed;
    /**
     * 	是否可用
     * 	0：是
     * 	1：否
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean usable;
    @ApiModelProperty(value = "输入类型")
    private Integer inputType;
    @ApiModelProperty(value = "输入数据")
    private String inputData;
    @ApiModelProperty(value = "输入类型")
    private String inputTypeName;

    public String getInputTypeName(){
        return AttrNameInputTypeEnum.getValueByCode(this.inputType);
    }

}
