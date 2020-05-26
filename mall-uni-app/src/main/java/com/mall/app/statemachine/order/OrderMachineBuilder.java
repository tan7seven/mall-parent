package com.mall.app.statemachine.order;

import com.mall.app.statemachine.order.action.OrderPayAction;
import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/25
 */
@Component
public class OrderMachineBuilder {
    public final static String MACHINE_ID = "order-machine";

    public final static String MESSAGE_HEADER = "ORDER_HEADER";

    @Autowired
    private OrderPayAction orderPayAction;


    public StateMachine<OrderStatusEnum, OrderEventEnum> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<OrderStatusEnum, OrderEventEnum> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINE_ID)
                .beanFactory(beanFactory);

        /** 创建订单 */
        builder.configureStates()
                .withStates()
                .initial(OrderStatusEnum.UNPAID)
//                .choice(OrderStatusEnum.WAITING_FOR_RECEIVE) 类似编程中的if else
                .states(EnumSet.allOf(OrderStatusEnum.class));

        builder.configureTransitions()
//                .withChoice()
//                .source(OrderStatusEnum.UNPAID) 类似编程中的if else
//                .first() if判断
//                .last() else 见https://blog.csdn.net/firebat/article/details/90178339

                /** 订单支付 */
                .withExternal()
                .source(OrderStatusEnum.UNPAID)
                .target(OrderStatusEnum.PAID_WAIT_DELIVER)
                .event(OrderEventEnum.PAY)
                .action(orderPayAction)

                /** 收货 */
                .and().withExternal()
                .source(OrderStatusEnum.PAID_WAIT_DELIVER)
                .target(OrderStatusEnum.RECEIVED)
                .event(OrderEventEnum.RECEIVE);



        return builder.build();
    }
}
