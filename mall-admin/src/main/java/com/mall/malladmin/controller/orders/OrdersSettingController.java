package com.mall.malladmin.controller.orders;

import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.orders.OrdersSettingDto;
import com.mall.malladmin.entity.orders.OrdersSettingEntity;
import com.mall.malladmin.service.orders.OrdersSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单配置
 */
@Slf4j
@RestController
@RequestMapping(value = "ordersSettingController")
public class OrdersSettingController {

    @Resource(name = "ordersSettingService")
    private OrdersSettingService ordersSettingService;

    /**
     * 获取订单配置信息
     * @return
     */
    @PostMapping(value = "getSetting.do")
    protected Object getSetting(){
        OrdersSettingEntity result = ordersSettingService.findOne();
        return new CommonResultDto().success(result);
    }

    /**
     * 修改
     * @param id
     * @param dto
     * @return
     */
    @PostMapping(value = "updateOrdersSetting.do/{id}")
    protected Object updateOrdersSetting(@PathVariable Integer id, OrdersSettingDto dto){
        ordersSettingService.update(id, dto);
        return new CommonResultDto().success();
    }
}
