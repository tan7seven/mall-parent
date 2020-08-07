package com.mall.manage.model.vo.order;

import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/8/4
 */
@Data
@ApiModel
public class OrderItemVO extends BaseVO {
    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    private Integer productAmount;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String picUrl;
    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;
    /**
     *	商品属性
     */
    @ApiModelProperty(value = "商品属性")
    private String productProperty;

    @ApiModelProperty(value = "小计")
    private BigDecimal itemTotal;

    public BigDecimal getItemTotal(){
        if (Objects.isNull(productAmount) || Objects.isNull(productPrice)) {
            return BigDecimal.ZERO;
        }
        return productPrice.multiply(BigDecimal.valueOf(productAmount));
    }

}
