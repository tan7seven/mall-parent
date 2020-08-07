package com.mall.app.model.vo.order;

import com.mall.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/29
 */
@Data
@ApiModel
public class OrderPageVO extends BaseModel {

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单状态名")
    private String orderStatusName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "订单明细")
    private List<OrderItem> item;

    @Data
    @ApiModel
    public static class OrderItem extends BaseModel {

        @ApiModelProperty(value = "商品ID")
        private Long productId;

        @ApiModelProperty(value = "SKU ID")
        private Long skuId;

        @ApiModelProperty(value = "商品名称")
        private String productName;

        @ApiModelProperty(value = "图片地址")
        private String picUrl;

        @ApiModelProperty(value = "价格")
        private BigDecimal skuPrice;

        @ApiModelProperty(value = "数量")
        private Integer skuAmount;

        @ApiModelProperty(value = "属性")
        private String attr;
    }
}
