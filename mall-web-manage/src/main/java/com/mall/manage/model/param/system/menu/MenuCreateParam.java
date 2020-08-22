package com.mall.manage.model.param.system.menu;

import com.mall.common.model.param.BaseParam;
import com.mall.dao.dto.system.MenuDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/9
 */
@Data
@ApiModel
public class MenuCreateParam extends BaseParam {
    /**
     * 父级菜单主键
     */
    @NotNull(message = "父级菜单不能为空")
    @ApiModelProperty(value = "父级菜单主键")
    private Long parentId;
    /**
     * 菜单编码
     */
    @ApiModelProperty(value = "菜单编码")
    private String menuCode;
    /**
     * 菜单标题
     */
    @ApiModelProperty(value = "菜单标题")
    private String menuTitle;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String menuUrl;
    /**
     * 是否显示
     */
    @NotNull(message = "是否显示不能为空")
    @ApiModelProperty(value = "是否显示")
    private Boolean hidden;
    /**
     * 图标路径
     */
    @ApiModelProperty(value = "图标路径")
    private String menuIcon;
    /**
     * 按钮列表
     */
    @ApiModelProperty(value = "按钮列表")
    private List<String> buttonList;

}
