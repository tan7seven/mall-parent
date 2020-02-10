package com.mall.manage.param.product.product;

import com.mall.common.param.CommonParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/1/7
 */
@Data
@ApiModel(value = "订单获取分页信息")
public class GetPageParam extends CommonParam {

    /**
     *商品编号
     */
    @ApiModelProperty(value = "商品编号")
    private Integer productId;
    /**
     * 分类编号
     */
    @ApiModelProperty(value = "分类编号")
    private Integer typeId;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 商品状态
     * 0：上架
     * 1：下架
     */
    @ApiModelProperty(value = "商品状态 0：上架 1：下架")
    private String isPutaway;

}
