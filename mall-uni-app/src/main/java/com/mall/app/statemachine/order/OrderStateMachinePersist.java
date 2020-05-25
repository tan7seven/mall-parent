package com.mall.app.statemachine.order;

import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.dao.entity.order.OrderEntity;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

/**
 * @author weijiazheng
 * @date 2020/5/8
 */
@Component
public class OrderStateMachinePersist implements StateMachinePersist<OrderStatusEnum, OrderEventEnum, OrderEntity> {
    @Override
    public void write(StateMachineContext<OrderStatusEnum, OrderEventEnum> stateMachineContext, OrderEntity orderDO) throws Exception {
        //这里不做任何持久化工作
    }

    @Override
    public StateMachineContext<OrderStatusEnum, OrderEventEnum> read(OrderEntity entity){
        return new DefaultStateMachineContext<OrderStatusEnum, OrderEventEnum>(OrderStatusEnum.getByCode(entity.getOrderStatus()), null, null, null, null, OrderMachineBuilder.MACHINE_ID);
    }
}
