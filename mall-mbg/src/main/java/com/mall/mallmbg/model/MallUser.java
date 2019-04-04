package com.mall.mallmbg.model;

import java.io.Serializable;
import java.util.Date;

public class MallUser implements Serializable {
    /**
     * 年月时分秒+3位随机数
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 登录账号
     *
     * @mbggenerated
     */
    private String loginId;

    /**
     * 密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 	昵称
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * 	手机号码
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 头像
     *
     * @mbggenerated
     */
    private String headPortrait;

    /**
     * 用户角色
     *
     * @mbggenerated
     */
    private String role;

    /**
     * 是否可用 0:可用，1：禁用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 注册时间
     *
     * @mbggenerated
     */
    private Date addDate;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", loginId=").append(loginId);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", phone=").append(phone);
        sb.append(", headPortrait=").append(headPortrait);
        sb.append(", role=").append(role);
        sb.append(", isUsable=").append(isUsable);
        sb.append(", addDate=").append(addDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}