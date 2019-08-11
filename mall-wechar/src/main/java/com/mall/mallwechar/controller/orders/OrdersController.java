package com.mall.mallwechar.controller.orders;

import com.mall.mallmodel.dto.orders.OrdersDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单信息
 */
@RestController
@RequestMapping(value = "ordersController")
public class OrdersController {


    @RequestMapping(value = "getList.do")
    protected Object getList(@RequestBody OrdersDto dto){
        return null;
    }

}
