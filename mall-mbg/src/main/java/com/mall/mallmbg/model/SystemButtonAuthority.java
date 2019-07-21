package com.mall.mallmbg.model;

import java.io.Serializable;

public class SystemButtonAuthority implements Serializable {
    /**
     * 	主键
     *
     * @mbggenerated
     */
    private String buttonAuthorityId;

    /**
     * 	用户主键
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 按钮主键
     *
     * @mbggenerated
     */
    private String buttonId;

    private static final long serialVersionUID = 1L;

    public String getButtonAuthorityId() {
        return buttonAuthorityId;
    }

    public void setButtonAuthorityId(String buttonAuthorityId) {
        this.buttonAuthorityId = buttonAuthorityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", buttonAuthorityId=").append(buttonAuthorityId);
        sb.append(", userId=").append(userId);
        sb.append(", buttonId=").append(buttonId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}