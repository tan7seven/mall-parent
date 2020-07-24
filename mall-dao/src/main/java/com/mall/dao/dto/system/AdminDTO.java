package com.mall.dao.dto.system;

import com.mall.dao.dto.common.CommonDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class AdminDTO extends CommonDTO implements Serializable{
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
     * 姓名
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
    private String role;
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
    /**
     * 是否被删除
     * 0-》未删除
     * 1-》已删除
     */
    private Boolean deleted;

    /**
     * 按钮列表
     */
    private List<ButtonDTO> buttonList;
    /**
     * 菜单ID
     */
    private String menuId;

}
