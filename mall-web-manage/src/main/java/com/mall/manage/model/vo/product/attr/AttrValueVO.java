package com.mall.manage.model.vo.product.attr;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AttrValueVO extends BaseVO {
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 属性名编码
     */
    @ApiModelProperty(value = "属性名编码")
    private Long nameId;
    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private Object value;

    @ApiModelProperty(value = "输入类型")
    private Integer inputType;
}
