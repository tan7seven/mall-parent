package com.mall.mallmodel.entity.system;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 系统按钮
 */
@Data
@Entity
@Table(name = "system_button")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ButtonEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32,name = "button_id")
    private String buttonId;
    /**
     * 菜单主键
     */
    @Column(length = 32,name = "menu_id")
    private String menuId;
    /**
     * 按钮编码
     */
    @Column(length = 64,name = "button_code")
    private String buttonCode;
    /**
     * 按钮名称
     */
    @Column(length = 64,name = "button_name")
    private  String buttonName;
}
