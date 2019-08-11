package com.mall.mallmodel.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@Entity
@Table(name = "mall_user")
public class UserEntity implements Serializable{
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
    @Column(length = 64,name = "login_code")
    private String loginCode;
    /**
     * 登录密码
     */
    @Column(length = 64,name = "password")
    private String password;
    /**
     * 昵称
     */
    @Column(length = 128,name = "name")
    private String name;
    /**
     * 电话号码
     */
    @Column(length = 64,name = "phone")
    private String phone;
    /**
     * 头像
     */
    @Column(length = 128,name = "head_portrait")
    private String headPortrait;
    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 邮箱
     */
    @Column(name = "score")
    private Integer score;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    @Column(length = 1,name = "is_usable")
    private String isUsable;
    /**
     * 新增时间
     */
    @Column(name = "create_Time")
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "modify_Time")
    private Date modifyTime;
}
