package com.mall.app.model.vo.product;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/27
 */
@Data
@ApiModel
public class ProductTypeVO extends BaseModel {
    @ApiModelProperty(value = "类目ID")
    private Long typeId;
    @ApiModelProperty(value = "上级ID")
    private Long parentId;
    @ApiModelProperty(value = "类目名")
    private String typeName;
    @ApiModelProperty(value = "图片地址")
    private String picUrl;

}
