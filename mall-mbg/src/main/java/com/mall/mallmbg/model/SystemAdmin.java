package com.mall.mallmbg.model;

import java.io.Serializable;
import java.util.Date;

public class SystemAdmin implements Serializable {
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
    private String loginCode;

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
    private String name;

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
    private String picUrl;

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
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 是否被删除，0-》未删除，1-》已删除
     *
     * @mbggenerated
     */
    private String isDelete;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", loginCode=").append(loginCode);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", role=").append(role);
        sb.append(", isUsable=").append(isUsable);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}