package com.mall.malladmin.controller.order;

import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.order.OrderSettingDTO;
import com.mall.malladmin.entity.order.OrderSettingEntity;
import com.mall.malladmin.service.order.OrderSettingService;
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
@RequestMapping(value = "orderSettingController")
public class OrderSettingController {

    @Resource(name = "orderSettingService")
    private OrderSettingService orderSettingService;

    @ApiOperation("获取订单配置信息")
    @PostMapping(value = "getSetting.do")
    protected CommonResultDTO getSetting(){
        OrderSettingEntity result = orderSettingService.findOne();
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('OMS:ORDERETTING:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateOrderSetting.do/{id}")
    protected CommonResultDTO updateOrderSetting(@PathVariable Integer id, OrderSettingDTO dto){
        orderSettingService.update(id, dto);
        return new CommonResultDTO().success();
    }
}
