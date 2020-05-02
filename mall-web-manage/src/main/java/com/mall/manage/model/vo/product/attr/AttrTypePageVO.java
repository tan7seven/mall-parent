package com.mall.manage.model.vo.product.attr;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/10
 */
@Data
@ApiModel
public class AttrTypePageVO extends BaseVO {
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 显示参数数量
     */
    @ApiModelProperty(value = "显示参数数量")
    private Integer paramNum;
    /**
     * 销售规格数量
     */
    @ApiModelProperty(value = "销售规格数量")
    private Integer attrNum;
}
