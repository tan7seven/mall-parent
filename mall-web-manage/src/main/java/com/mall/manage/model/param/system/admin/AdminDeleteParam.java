package com.mall.manage.model.param.system.admin;

import com.mall.manage.model.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/23
 */
@Data
@ApiModel
public class AdminDeleteParam extends BaseParam {

    @NotEmpty(message = "账号id不能为空")
    @NotNull(message = "账号id不能为空")
    @ApiModelProperty(value = "账号id")
    private List<Long> ids;

}
