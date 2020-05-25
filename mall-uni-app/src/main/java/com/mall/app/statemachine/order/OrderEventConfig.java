package com.mall.app.statemachine.order;

import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/25
 */
@Slf4j
@WithStateMachine
public class OrderEventConfig {

    @OrderOnTransition(target = OrderStatusEnum.UNPAID)
    public void create(Message<OrderEventEnum> message) {
        log.info(message.getHeaders().get("order").toString());
        log.info("订单创建，待支付");
    }

    @OrderOnTransition(source = OrderStatusEnum.UNPAID, target = OrderStatusEnum.PAID_WAIT_DELIVER)
    public void pay(Message<OrderEventEnum> message) {
        log.info(message.getHeaders().get("order").toString());
        log.info("用户完成支付，待收货");
    }

    @OrderOnTransition(source = OrderStatusEnum.PAID_WAIT_DELIVER, target = OrderStatusEnum.RECEIVED)
    public void receive(Message<OrderEventEnum> message) {
        log.info(message.getHeaders().get("order").toString());
        log.info("用户已收货，订单完成:{}", message);
    }

    @OnStateChanged
    public void change() {
//        监听所有订单变化 做统一处理 如发送队列消息，
        log.info("状态变化");
    }

}
