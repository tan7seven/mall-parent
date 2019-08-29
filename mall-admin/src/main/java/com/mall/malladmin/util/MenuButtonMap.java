package com.mall.malladmin.util;

import java.util.HashMap;

/**
 * 菜单按钮
 */
public class MenuButtonMap {
    public static HashMap<String, String > MENU_BUTTON_MAP = new HashMap<>();
    static{
        MENU_BUTTON_MAP.put("添加", "ADD");
        MENU_BUTTON_MAP.put("修改", "UPDATE");
        MENU_BUTTON_MAP.put("删除", "DELETE");
        MENU_BUTTON_MAP.put("开关", "SWITCH");
    }
}
