package com.mall.manage.model.param.system.admin;

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
public class AdminUsableUpdateParam extends BaseParam {
    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean usabled;

    public Boolean isUsabled(){
        if (null == this.usabled) {
            return Boolean.FALSE;
        }
        return this.usabled;
    }
}
