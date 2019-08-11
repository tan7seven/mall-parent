package com.mall.mallmodel.entity.system;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * 系统菜单
 */
@Data
@Entity
@Table(name = "system_menu")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MenuEntity {
    /**
     * 菜单主键
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32,name = "menu_id")
    private String menuId;
    /**
     * 父级菜单主键
     */
    @Column(length = 32,name = "parent_id")
    private String parentId;
    /**
     * 菜单编码
     */
    @Column(length = 64,name = "menu_code")
    private String menuCode;
    /**
     * 菜单标题
     */
    @Column(length = 64,name = "menu_title")
    private String menuTitle;
    /**
     * 菜单路径
     */
    @Column(length = 64,name = "menu_url")
    private String menuUrl;
    /**
     * 图标路径
     */
    @Column(length = 64,name = "menu_icon")
    private String menuIcon;
    /**
     * 图标路径
     */
    @Column(length = 1,name = "is_Hidden")
    private String isHidden;
    /**
     * 排序
     */
    @Column()
    private Integer sort;
}
