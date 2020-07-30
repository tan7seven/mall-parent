package com.mall.app.controller.order;

import com.mall.app.model.param.order.WXOrderPayNoticeParam;
import com.mall.app.service.order.OrderService;
import com.mall.common.model.vo.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/29
 */
@Slf4j
@Api(tags = "支付通知模块")
@RestController
@RequestMapping(value = "/v1/pub/pay-notice")
public class PayNoticeController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order/wx")
    @ApiOperation(value = "微信支付回调通知")
    public RestResult<Boolean> wxOrderPayNotice(WXOrderPayNoticeParam param){
        return RestResult.success(orderService.wxOrderPayNotice(param));
    }

}
