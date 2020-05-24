package com.mall.app.model.param.cart;

import com.mall.dao.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/23
 */
@Data
@ApiModel
public class CartRemoveParam extends BaseParam {
    @NotEmpty(message = "主键不能为空")
    @ApiModelProperty(value = "主键集合")
    private List<Long> idList;
}
