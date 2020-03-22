package com.mall.dao.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.dao.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@TableName("mall_user")
public class UserEntity extends BaseEntity {
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
    private String headPortrait;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱
     */
    private Integer score;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    private Boolean isUsable;
    /**
     * 新增时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifyTime;
}
