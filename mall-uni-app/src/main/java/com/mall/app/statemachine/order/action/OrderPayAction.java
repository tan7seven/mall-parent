package com.mall.app.statemachine.order.action;

import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.dao.entity.order.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/26
 */
@Slf4j
@Component
public class OrderPayAction  extends OrderAction {

    @Override
    void execute(StateContext<OrderStatusEnum, OrderEventEnum> stateContext, OrderEntity orderDO) {
        log.info("OrderPayAction");
    }
}
