package com.mall.app.model.vo.advert;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 广告列表-旋转木马
 */
@Data
@ApiModel
public class AdvertCarouseVO extends BaseModel {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "跳转地址-商品ID")
    private String skipUrl;

    @ApiModelProperty(value = "背景颜色")
    private String backColor;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;
}
