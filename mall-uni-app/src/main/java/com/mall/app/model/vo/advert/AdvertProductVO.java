package com.mall.app.model.vo.advert;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel
public class AdvertProductVO extends BaseModel {
    /**
     * 广告ID
     */
    @ApiModelProperty(value = "广告ID")
    private Long advertId;
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long productId;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String picUrl;
    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
}
