package com.mall.app.controller.order;

import com.mall.app.statemachine.order.OrderMachineManage;
import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.order.OrderEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/25
 */

@Slf4j
@Api(value = "订单模块")
@RestController
@RequestMapping(value = "/v1/order")
public class OrderController {
    @Autowired
    private OrderMachineManage orderStateMachineManage;

    @PutMapping(value = "/create")
    @ApiOperation(value = "创建订单")
    public RestResult<String> test1(Integer userId) throws Exception {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId("UNPAID");
        orderEntity.setOrderStatus(OrderStatusEnum.UNPAID.getCode());
        orderEntity.setFreightPrice(BigDecimal.ONE);
        Message<OrderEventEnum> message = MessageBuilder.withPayload(OrderEventEnum.PAY).setHeader("order", orderEntity).build();
        orderStateMachineManage.sendEvent(message, orderEntity);
        return RestResult.success();

    }
}
