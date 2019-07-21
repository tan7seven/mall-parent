package com.mall.mallmbg.model;

import java.io.Serializable;

public class SystemMenuAuthority implements Serializable {
    /**
     * 	主键
     *
     * @mbggenerated
     */
    private String menuAuthorityId;

    /**
     * 	用户主键
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 菜单主键
     *
     * @mbggenerated
     */
    private String menuId;

    private static final long serialVersionUID = 1L;

    public String getMenuAuthorityId() {
        return menuAuthorityId;
    }

    public void setMenuAuthorityId(String menuAuthorityId) {
        this.menuAuthorityId = menuAuthorityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuAuthorityId=").append(menuAuthorityId);
        sb.append(", userId=").append(userId);
        sb.append(", menuId=").append(menuId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}