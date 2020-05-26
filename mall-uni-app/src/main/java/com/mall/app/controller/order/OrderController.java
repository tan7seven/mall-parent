package com.mall.app.controller.order;

import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.service.order.OrderService;
import com.mall.app.statemachine.order.OrderMachineManage;
import com.mall.common.model.vo.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/pay-detail/get")
    @ApiOperation(value = "支付详情")
    public RestResult<Boolean> payDetail(@RequestBody @Validated OrderCreateParam param){
        log.info("123：{}", param);
        return RestResult.success(Boolean.TRUE);

    }
}
