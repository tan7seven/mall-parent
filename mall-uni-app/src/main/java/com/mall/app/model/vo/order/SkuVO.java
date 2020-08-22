package com.mall.app.model.vo.order;

import com.mall.app.model.BaseSkuModel;
import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/27
 */
@Data
@ApiModel
public class SkuVO extends BaseSkuModel {
    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "SKU图片地址")
    private String picUrl;
    @ApiModelProperty(value = "SKU属性数据")
    private String attrVal;

}
