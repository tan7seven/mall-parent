package com.mall.manage.model.param.system.menu;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Data
public class MenuAuthConfirmParam extends BaseParam {
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 菜单ID列表
     */
    @ApiModelProperty(value = "菜单ID列表")
    private List<Long> menuList;

}
