package com.mall.manage.model.vo.order;

import com.mall.common.enums.OrderStatusEnum;
import com.mall.manage.model.vo.product.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/8/4
 */
@Data
@ApiModel
public class OrderOperationLogVO extends BaseVO {
    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名")
    private String name;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
    private String orderStatusName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getOrderStatusName(){
        if (Objects.isNull(orderStatus)) {
            return OrderStatusEnum.UNPAID.getDesc();
        }
        return OrderStatusEnum.getByCode(this.orderStatus).getDesc();
    }
}
