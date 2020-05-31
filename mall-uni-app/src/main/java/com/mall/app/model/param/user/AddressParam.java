package com.mall.app.model.param.user;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class AddressParam extends BaseModel {

    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 收货人姓名
     */
    @NotEmpty(message = "收货人姓名不能为空")
    @ApiModelProperty(value = "收货人姓名")
    private String name;
    /**
     * 收货人电话
     */
    @NotEmpty(message = "收货人电话不能为空")
    @ApiModelProperty(value = "收货人电话")
    private String mobile;
    /**
     * 省、直辖市
     */
    @ApiModelProperty(value = "省、直辖市")
    private String province;
    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;
    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String region;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;
    /**
     * 默认收货地址 ：0->否；1->是
     */
    @ApiModelProperty(value = "默认收货地址 ：0->否；1->是")
    private Boolean defaulted;

    public Boolean getDefaulted(){
        return null == this.defaulted ? Boolean.FALSE : this.defaulted;
    }
}
