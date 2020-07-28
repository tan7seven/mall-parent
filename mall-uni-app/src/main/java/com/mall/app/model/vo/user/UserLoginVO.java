package com.mall.app.model.vo.user;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/28
 */
@Data
@ApiModel
public class UserLoginVO extends BaseModel {

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String headPortrait;
}
