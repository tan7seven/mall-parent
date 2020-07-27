package com.mall.manage.model.vo.system;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class MenuDetailVO extends BaseVO {

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

    /**
     * 图标路径
     */
    @ApiModelProperty(value = "图标路径")
    private String menuIcon;
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
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "按钮列表")
    private List<MenuDetailButtonVO> buttonList;

    @Data
    @ApiModel
    public static class  MenuDetailButtonVO{
        @ApiModelProperty(value = "主键")
        private Long id;

        @ApiModelProperty(value = "按钮名称")
        private String buttonName;
    }
}
