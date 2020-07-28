package com.mall.app.model.param.user;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/28
 */
@Data
@ApiModel
public class UserLoginParam extends BaseModel {

    @NotBlank(message = "电话号码不能为空")
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
