package com.mall.app.model.vo.advert;

import com.mall.common.enums.AdvertTypeEnum;
import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class AdvertVO extends BaseModel {

    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 类型
     * @see AdvertTypeEnum
     */
    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "跳转地址")
    private String skipUrl;

    @ApiModelProperty(value = "背景颜色")
    private String backColor;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @ApiModelProperty(value = "商品列表")
    private List<AdvertProductVO> productList;
}
