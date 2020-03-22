package com.mall.manage.model.param.product.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/1/14
 */
@Data
@ApiModel
public class UpdateIsPutawayParam {
    @ApiModelProperty("是否上下架 0上架 1下架")
    @NotBlank(message = "是否上下架不能为空")
    private Boolean isPutaway;
    @ApiModelProperty("id列表")
    @NotEmpty(message = "ID不能为空")
    private List<Integer> ids;
}
