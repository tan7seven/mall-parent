package com.mall.app.statemachine;

import com.mall.app.statemachine.order.OrderStateMachinePersist;
import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.dao.entity.order.OrderEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;

/**
 * @author weijiazheng
 * @date 2020/5/8
 */
@Configuration
public class PersistConfig {

    @Resource
    private OrderStateMachinePersist orderStateMachinePersist;

    @Bean(name = "orderPersister")
    public StateMachinePersister<OrderStatusEnum, OrderEventEnum, OrderEntity> orderPersister() {
        return new DefaultStateMachinePersister<OrderStatusEnum, OrderEventEnum, OrderEntity>(orderStateMachinePersist);
    }
}
