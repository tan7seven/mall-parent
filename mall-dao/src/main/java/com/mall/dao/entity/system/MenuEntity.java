package com.mall.dao.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;


/**
 * 系统菜单
 */
@Data
@TableName("system_menu")
public class MenuEntity {

    public static final Integer IS_HIDDEN = 1;
    public static final Integer NOT_HIDDEN = 0;
    /**
     * 菜单主键
     */
    @Id
    private String menuId;
    /**
     * 父级菜单主键
     */
    private String parentId;
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
    private Integer isHidden;

}
