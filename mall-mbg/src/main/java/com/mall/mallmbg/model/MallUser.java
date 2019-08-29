package com.mall.mallmbg.model;

import java.io.Serializable;
import java.util.Date;

public class MallUser implements Serializable {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 头像
     *
     * @mbggenerated
     */
    private String headPortrait;

    /**
     * 	是否可用 	0:可用，1：禁用
     *
     * @mbggenerated
     */
    private String isUsable;

    /**
     * 登录账号
     *
     * @mbggenerated
     */
    private String loginCode;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * 用户名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 登录密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 电话号码
     *
     * @mbggenerated
     */
    private String phone;

    private Integer score;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", email=").append(email);
        sb.append(", headPortrait=").append(headPortrait);
        sb.append(", isUsable=").append(isUsable);
        sb.append(", loginCode=").append(loginCode);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", score=").append(score);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}