package com.mall.dao.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@Data
@TableName("system_admin")
public class AdminEntity extends BaseEntity {
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
    private String name;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 头像
     */
    private String picUrl;
    /**
     * 角色
     * 0:ROLE_USER
     * 1:ROLE_ADMIN
     */
    private Integer role;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    @TableField(value = "is_usabled")
    private Boolean usabled;
    /**
     * 状态
     * 0:正常
     * 1：已删除
     */
    @TableLogic(value="0",delval="1")
    @TableField(value = "is_deleted")
    private Boolean deleted;
}
