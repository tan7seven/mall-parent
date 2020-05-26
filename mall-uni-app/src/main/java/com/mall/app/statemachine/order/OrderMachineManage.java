package com.mall.app.statemachine.order;

import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.dao.entity.order.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author weijiazheng
 * @date 2020/5/13
 */
@Slf4j
@Service
public class OrderMachineManage {

    @Resource
    private BeanFactory beanFactory;
    @Resource
    private OrderMachineBuilder machineBuilder;
    @Resource
    private StateMachinePersister<OrderStatusEnum, OrderEventEnum, OrderEntity> orderPersister;

    public synchronized boolean sendEvent(Message message, OrderEntity entity) {
        boolean result = false;
        try {
            StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine = machineBuilder.build(beanFactory);
            stateMachine.start();
            orderPersister.restore(stateMachine, entity);
            result = stateMachine.sendEvent(message);
        } catch (Exception e) {
            log.error("恢复状态机时异常：", e);
        }
        return result;
    }
}
