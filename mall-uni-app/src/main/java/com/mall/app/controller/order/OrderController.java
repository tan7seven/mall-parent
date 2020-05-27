package com.mall.app.controller.order;

import com.alibaba.fastjson.JSONObject;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.vo.order.CreateOrderVO;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.app.service.order.OrderService;
import com.mall.app.statemachine.order.OrderMachineManage;
import com.mall.common.model.vo.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public RestResult<PayDetailVO> payDetail(@ApiParam(value = "SKU集合") @RequestParam List<Long> skuIdList){
        Long userId = 123L;
        PayDetailVO result = orderService.payDetail(skuIdList, userId);
        return RestResult.success(result);

    }

    @PutMapping(value = "/create")
    @ApiOperation(value = "提交订单")
    public RestResult<CreateOrderVO> create(@RequestBody @Validated OrderCreateParam param){
        Long userId = 123L;
        CreateOrderVO result = orderService.createOrder(param, userId);
        return RestResult.success(result);
    }
}
