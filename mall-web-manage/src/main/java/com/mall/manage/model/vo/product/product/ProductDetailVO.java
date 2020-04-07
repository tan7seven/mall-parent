package com.mall.manage.model.vo.product.product;

import com.mall.manage.model.vo.product.BaseVO;
import com.mall.manage.model.vo.product.attr.AttrNameVO;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class ProductDetailVO extends BaseVO {
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "明细")
    private String detail;
    @ApiModelProperty(value = "计量单位")
    private String unit;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty("类目ID")
    private Long productTypeId;
    @ApiModelProperty("上级类目ID")
    private Long productTypeParentId;
    @ApiModelProperty(value = "主图地址")
    private String picUrl;
    @ApiModelProperty(value = "轮播图")
    private List<String> picList;
    @ApiModelProperty(value = "商品属性名")
    private List<AttrNameVO> attrNameVOList;
    @ApiModelProperty(value = "商品属性值")
    private List<AttrValueVO> attrValueVOList;
}
