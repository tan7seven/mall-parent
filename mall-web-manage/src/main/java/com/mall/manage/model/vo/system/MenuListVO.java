package com.mall.manage.model.vo.system;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/29
 */
@Data
@ApiModel
public class MenuListVO extends BaseVO {

    /**
     * 菜单主键
     */
    @ApiModelProperty(value = "菜单主键")
    private Long menuId;
    /**
     * 父级菜单主键
     */
    @ApiModelProperty(value = "父级菜单主键")
    private Long parentId;

    @ApiModelProperty(value = "父级菜单主键名称")
    private String parentTitle;
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

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;
    /**
     * 子级菜单
     */
    @ApiModelProperty(value = "子级菜单")
    private List<MenuListVO> children;
    /**
     * 是否有子级
     */
    @ApiModelProperty(value = "是否有子级")
    private Boolean hasChildren = true;
}
