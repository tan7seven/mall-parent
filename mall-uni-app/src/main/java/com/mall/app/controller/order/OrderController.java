package com.mall.app.controller.order;

import com.mall.app.model.param.order.BuildPayDetailParam;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.vo.order.CreateOrderVO;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.app.service.order.OrderService;
import com.mall.common.model.vo.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/25
 */

@Slf4j
@Api(tags = "订单模块")
@RestController
@RequestMapping(value = "/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "支付详情")
    @PostMapping(value = "/build/pay-detail")
    public RestResult<PayDetailVO> buildPayDetail(@RequestBody @Validated BuildPayDetailParam param){
        Long userId = 123L;
        PayDetailVO result = orderService.payDetail(param, userId);
        return RestResult.success(result);
    }

    @ApiOperation(value = "提交订单")
    @PutMapping(value = "/create")
    public RestResult<CreateOrderVO> create(@RequestBody @Validated OrderCreateParam param){
        Long userId = 123L;
        CreateOrderVO result = orderService.createOrder(param, userId);
        return RestResult.success(result);
    }
}
