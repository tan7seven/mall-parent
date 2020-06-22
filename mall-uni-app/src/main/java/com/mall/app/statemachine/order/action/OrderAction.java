package com.mall.app.statemachine.order.action;

import com.mall.app.statemachine.order.OrderMachineBuilder;
import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.dao.entity.order.OrderEntity;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * @author huanggs
 * @date 2020/5/22
 */
public abstract class OrderAction implements Action<OrderStatusEnum, OrderEventEnum> {

    abstract void execute(StateContext<OrderStatusEnum, OrderEventEnum> stateContext, OrderEntity orderDO);

    @Override
    public void execute(StateContext<OrderStatusEnum, OrderEventEnum> stateContext) {
        OrderEntity orderDO = (OrderEntity) stateContext.getMessageHeader(OrderMachineBuilder.MESSAGE_HEADER);
        execute(stateContext, orderDO);
    }
}
