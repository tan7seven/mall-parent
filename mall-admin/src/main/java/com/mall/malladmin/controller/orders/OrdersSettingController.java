package com.mall.malladmin.controller.orders;

import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.orders.OrdersSettingDto;
import com.mall.malladmin.entity.orders.OrdersSettingEntity;
import com.mall.malladmin.service.orders.OrdersSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "订单配置", tags = "订单配置")
@Slf4j
@RestController
@RequestMapping(value = "ordersSettingController")
public class OrdersSettingController {

    @Resource(name = "ordersSettingService")
    private OrdersSettingService ordersSettingService;

    @ApiOperation("获取订单配置信息")
    @PostMapping(value = "getSetting.do")
    protected CommonResultDto getSetting(){
        OrdersSettingEntity result = ordersSettingService.findOne();
        return new CommonResultDto().success(result);
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('OMS:ORDERSETTING:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateOrdersSetting.do/{id}")
    protected CommonResultDto updateOrdersSetting(@PathVariable Integer id, OrdersSettingDto dto){
        ordersSettingService.update(id, dto);
        return new CommonResultDto().success();
    }
}
