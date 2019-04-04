package com.mall.malladmin.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@Entity
@Table(name = "mall_user")
public class UserEntity implements Serializable {
    /**
     * 是否可用：可用
     */
    public static final String IS_USABLE = "0";
    /**
     * 是否可用：禁用
     */
    public static final String NOT_USABLE = "1";


    /**
     * 用户编号
     */
    @Id
    @Column(length = 15,name = "user_id")
    private String userId;
    /**
     * 登录账号
     */
    @Column(length = 64,name = "login_id")
    private String loginId;
    /**
     * 登录密码
     */
    @Column(length = 64,name = "password")
    private String password;
    /**
     * 昵称
     */
    @Column(length = 128,name = "nickname")
    private String nickname;
    /**
     * 电话号码
     */
    @Column(length = 11,name = "phone")
    private String phone;
    /**
     * 头像
     */
    @Column(length = 11,name = "head_portrait")
    private String headPortrait;
    /**
     * 角色
     * 0:ROLE_USER
     * 1:ROLE_ADMIN
     */
    @Column(length = 11,name = "role")
    private String role;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    @Column(length = 11,name = "is_usable")
    private String isUsable;
    /**
     * 新增时间
     */
    @Column(name = "add_date")
    private Date addDate;
    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

}
