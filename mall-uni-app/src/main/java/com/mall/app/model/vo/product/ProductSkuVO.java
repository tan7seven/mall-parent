package com.mall.app.model.vo.product;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class ProductSkuVO extends BaseModel {
    @ApiModelProperty(value = "SKU ID")
    private Long skuId;
    @ApiModelProperty(value = "SKU图片地址")
    private String picUrl;
    @ApiModelProperty(value = "SKU属性数据")
    private String attrJson;
    @ApiModelProperty(value = "库存")
    private Integer stock;
    @ApiModelProperty(value = "attr 信息")
    private List<ProductAttrValueVO> attrValueVO;
}
