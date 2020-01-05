package com.mall.malladmin.dto.system;

import lombok.Data;

/**
 * 系统按钮
 */
@Data
public class ButtonDTO {
    /**
     * 主键
     */
    private String buttonId;
    /**
     * 菜单主键
     */
    private String menuId;
    /**
     * 按钮编码
     */
    private String buttonCode;
    /**
     * 按钮名称
     */
    private  String buttonName;
}
