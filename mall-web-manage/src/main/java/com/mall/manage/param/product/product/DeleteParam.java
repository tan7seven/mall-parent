package com.mall.manage.param.product.product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/1/14
 */
@Data
@Api
public class DeleteParam {
    @ApiModelProperty("ID列表")
    @NotEmpty(message = "ID列表不能为空")
    private List<Integer> ids;
}
