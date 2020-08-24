package com.mall.manage.model.vo.system;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/22
 */
@Data
@ApiModel
public class AdminDetailVO extends BaseVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginCode;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String name;
    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private String phone;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean usabled;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String picUrl;

    /**
     * 角色
     * 0:ROLE_USER
     * 1:ROLE_ADMIN
     */
    @ApiModelProperty(value = "角色 1普通 2管理员")
    private Integer role;
}
