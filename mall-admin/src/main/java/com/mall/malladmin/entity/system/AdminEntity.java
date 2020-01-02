package com.mall.malladmin.entity.system;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@Entity
@Table(name = "system_admin")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class AdminEntity implements Serializable {

    /**
     * 普通
     */
    public static final String ROLE_USER = "0";
    /**
     * 管理员
     */
    public static final String ROLE_ADMIN ="1";
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 用户编号
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(length = 32,name = "user_id")
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
    @Column(length = 11,name = "phone")
    private String phone;
    /**
     * 头像
     */
    @Column(name = "pic_Url")
    private String picUrl;
    /**
     * 角色
     * 0:ROLE_USER
     * 1:ROLE_ADMIN
     */
    @Column(length = 64,name = "role")
    private String role;
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
    /**
     * 是否被删除
     * 0-》未删除
     * 1-》已删除
     */
    private String isDelete;
}
