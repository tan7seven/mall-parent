package com.mall.malladmin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class AdminVo implements Serializable {
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
    private String userId;
    /**
     * 登录账号
     */
    private String loginId;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 角色
     * 0:ROLE_USER
     * 1:ROLE_ADMIN
     */
    private String role;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    private String isUsable;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;
}
