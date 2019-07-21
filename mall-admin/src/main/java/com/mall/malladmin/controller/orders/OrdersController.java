package com.mall.malladmin.controller.orders;


import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.orders.OrdersDto;
import com.mall.malladmin.service.orders.OrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单信息
 */
@RestController
@RequestMapping(value = "/ordersController")
public class OrdersController extends GenericController{

    @Resource(name = "ordersService")
    private OrdersService ordersService;

    @PostMapping(value = "getPage.do")
    protected CommonResultDto getPage(@RequestBody OrdersDto dto){
        PageInfo<OrdersDto> result = ordersService.getPage(dto);
        return new CommonResultDto().success(result);
    }

    /**
     * 根据主键获取订单跟订单明细信息
     * @param id
     * @return
     */
    @GetMapping(value = "getOrders.do/{id}")
    protected  Object getOrders(@PathVariable String id){
        if(null == id){
            return new CommonResultDto().validateFailed("ID为空！");
        }
        OrdersDto result = ordersService.getOrdersById(id);
        return new CommonResultDto().success(result);
    }

    /**
     * 修改订单收货信息
     * @param dto
     * @return
     */
    @PostMapping(value = "updateReceiverInfo.do")
    protected  Object updateReceiverInfo(@RequestBody OrdersDto dto){
        ordersService.updateReceiverInfo(dto, this.getUserDetails());
        return new CommonResultDto().success();
    }
}
