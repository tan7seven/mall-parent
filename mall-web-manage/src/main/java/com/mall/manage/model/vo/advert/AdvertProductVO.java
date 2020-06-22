package com.mall.manage.model.vo.advert;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/1
 */
@Data
@ApiModel
public class AdvertProductVO extends BaseVO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

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
