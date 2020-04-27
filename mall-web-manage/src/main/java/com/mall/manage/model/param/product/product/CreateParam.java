package com.mall.manage.model.param.product.product;

import com.mall.common.model.param.CommonParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/1/7
 */
@Data
@ApiModel
public class CreateParam extends CommonParam {
    @NotBlank(message = "商品名称不能为空")
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "明细")
    private String detail;
    @ApiModelProperty(value = "计量单位")
    private String unit;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @NotNull(message = "属性类目ID不能为空")
    @ApiModelProperty("属性类目ID")
    private Long attrTypeId;
    @NotNull(message = "商品类目ID不能为空")
    @ApiModelProperty("商品类目ID")
    private Long productTypeId;
    @NotBlank(message = "商品主图不能为空")
    @ApiModelProperty(value = "主图地址")
    private String picUrl;
    @ApiModelProperty(value = "轮播图")
    private List<String> picList;
    @ApiModelProperty(value = "商品属性")
    private String attrValueString;

}
