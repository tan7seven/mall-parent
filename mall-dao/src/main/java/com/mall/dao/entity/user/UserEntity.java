package com.mall.dao.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@Data
@TableName("mall_user")
public class UserEntity extends BaseEntity {

    /**
     * 登录账号
     */
    private String loginCode;
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
    private String mobile;
    /**
     * 头像
     */
    private String portrait;

    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    @TableField(value ="is_usable")
    private Boolean usabled;
}
