package com.mall.mallmbg.model;

import java.io.Serializable;

public class SystemButton implements Serializable {
    /**
     * 	按钮主键	
     *
     * @mbggenerated
     */
    private String buttonId;

    /**
     * 	菜单ID
     *
     * @mbggenerated
     */
    private String menuId;

    /**
     * 按钮编码
     *
     * @mbggenerated
     */
    private String buttonCode;

    /**
     * 按钮名称
     *
     * @mbggenerated
     */
    private String buttonName;

    private static final long serialVersionUID = 1L;

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", buttonId=").append(buttonId);
        sb.append(", menuId=").append(menuId);
        sb.append(", buttonCode=").append(buttonCode);
        sb.append(", buttonName=").append(buttonName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}