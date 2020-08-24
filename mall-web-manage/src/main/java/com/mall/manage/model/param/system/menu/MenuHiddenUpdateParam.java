package com.mall.manage.model.param.system.menu;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/27
 */
@Data
@ApiModel
public class MenuHiddenUpdateParam extends BaseParam {

    @NotNull(message = "菜单ID不能为空")
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    public Boolean isHidden(){
        if (null == this.hidden) {
            return Boolean.FALSE;
        }
        return this.hidden;
    }
}
