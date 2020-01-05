package com.mall.mallwechar.controller.order;

import com.mall.mallmodel.dto.order.OrderDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单信息
 */
@RestController
@RequestMapping(value = "orderController")
public class OrderController {


    @RequestMapping(value = "getList.do")
    protected Object getList(@RequestBody OrderDto dto){
        return null;
    }

}
