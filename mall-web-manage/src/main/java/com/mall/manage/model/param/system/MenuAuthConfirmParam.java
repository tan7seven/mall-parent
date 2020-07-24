package com.mall.manage.model.param.system;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class MenuAuthConfirmParam extends BaseParam {
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginCode;
    /**
     * 菜单ID列表
     */
    @ApiModelProperty(value = "菜单ID列表")
    private List<Long> menuList;

}
