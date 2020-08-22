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
public class AdminPageVO extends BaseVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginCode;
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
     * 角色
     * 0:ROLE_USER
     * 1:ROLE_ADMIN
     */
    @ApiModelProperty(value = "角色")
    private Integer role;
    /**
     * 是否可用
     * 0：可用
     * 1：禁用
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean usabled;
    /**
     * 新增时间
     */
    @ApiModelProperty(value = "新增时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date modifyTime;
}
