package com.mall.manage.model.param.system.admin;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/23
 */
@Data
@ApiModel
public class AdminUpdateParam extends BaseParam {

    /**
     * 登录账号
     */
    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    @ApiModelProperty(value = "登录密码")
    private String password;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String name;
    /**
     * 电话号码
     */
    @NotBlank(message = "电话号码不能为空")
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
    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "头像")
    private String picUrl;

    @NotNull(message = "角色不能为空")
    @ApiModelProperty(value = "角色 1普通 2管理员")
    private Integer role;

    public Boolean isUsabled(){
        if (Objects.isNull(this.usabled)) {
            return Boolean.FALSE;
        }
        return this.usabled;
    }
}
