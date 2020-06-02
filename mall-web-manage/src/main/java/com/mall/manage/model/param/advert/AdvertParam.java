package com.mall.manage.model.param.advert;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/2
 */
@Data
@ApiModel
public class AdvertParam extends BaseParam {
    @ApiModelProperty(value = "主键")
    private Long id;

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    @NotNull(message = "类型不能为空")
    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "图片地址")
    private String picUrl;

    @ApiModelProperty(value = "跳转地址")
    private String skipUrl;

    @ApiModelProperty(value = "背景颜色")
    private String backColor;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
