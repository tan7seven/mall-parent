package com.mall.manage.model.param.system.menu;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/27
 */
@Data
@ApiModel
public class MenuDeleteParam extends BaseParam {

    @NotEmpty(message = "菜单id不能为空")
    @NotNull(message = "菜单id不能为空")
    @ApiModelProperty(value = "账号id")
    private List<Long> ids;
}
