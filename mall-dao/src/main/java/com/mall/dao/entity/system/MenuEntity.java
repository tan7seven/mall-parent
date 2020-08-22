package com.mall.dao.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;


/**
 * 系统菜单
 */
@Data
@TableName("system_menu")
public class MenuEntity extends BaseEntity {

    /**
     * 父级菜单主键
     */
    private Long parentId;
    /**
     * 菜单编码
     */
    private String menuCode;
    /**
     * 菜单标题
     */
    private String menuTitle;
    /**
     * 菜单路径
     */
    private String menuUrl;
    /**
     * 图标路径
     */
    private String menuIcon;
    /**
     * 是否隐藏
     */
    @TableField(value = "is_hidden")
    private Boolean hidden;

}
