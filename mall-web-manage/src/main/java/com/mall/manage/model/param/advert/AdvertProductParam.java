package com.mall.manage.model.param.advert;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/2
 */
@Data
@ApiModel
public class AdvertProductParam extends BaseParam {
    @ApiModelProperty(value = "主键")
    private Long id;

    @NotNull(message = "广告ID不能为空")
    @ApiModelProperty(value = "广告ID")
    private Long advertId;

    @NotNull(message = "商品ID不能为空")
    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;
}
