package com.mall.malladmin.controller.orders;


import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.orders.OrdersDto;
import com.mall.malladmin.service.orders.OrdersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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
        if(null == id || "undefined".equals(id)){
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
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateReceiverInfo.do")
    protected  Object updateReceiverInfo(@RequestBody OrdersDto dto){
        ordersService.updateReceiverInfo(dto, this.getUserDetails());
        return new CommonResultDto().success();
    }

    /**
     * 修改订单金额
     * @param dto
     * @return
     */
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateMoneyInfo.do")
    protected Object updateMoneyInfo(@RequestBody OrdersDto dto){
        if(null == dto.getDiscountPrice()){
            dto.setDiscountPrice(new BigDecimal(0));
        }
        ordersService.updateMoneyInfo(dto, this.getUserDetails());
        return new CommonResultDto().success();
    }

    /**
     * 修改订单备注
     * @param dto
     * @return
     */
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateRemarkInfo.do")
    protected Object updateRemarkInfo( OrdersDto dto){
        ordersService.updateRemarkInfo(dto, this.getUserDetails());
        return new CommonResultDto().success();
    }

    /**
     * 关闭订单 -> 手动关闭的订单为无效订单
     * @param dto
     * @return
     */
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "closeOrders.do")
    protected Object closeOrders(OrdersDto dto){
        ordersService.closeOrders(dto, this.getUserDetails());
        return new CommonResultDto().success();
    }
    /**
     * 关闭订单 -> 手动关闭的订单为无效订单
     * @return
     */
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "closeOrdersList.do")
    protected Object closeOrdersList(String[] ids, String remark){
        if (null ==ids || StringUtils.isBlank(ids.toString())) {
            return new CommonResultDto().success();
        }
        ordersService.closeOrdersList(ids, remark, this.getUserDetails());
        return new CommonResultDto().success();
    }

    /**
     * 删除订单信息
     * @param ids
     * @return
     */
    @PreAuthorize(" hasAuthority('OMS:ORDER:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteOrders.do")
    protected  Object deleteOrders(String[] ids){
        if (StringUtils.isBlank(ids.toString())) {
            return new CommonResultDto().validateFailed("参数为空！");
        }
        ordersService.deleteOrders(ids, this.getUserDetails());
        return new CommonResultDto().success();
    }

    /**
     * 批量发货
     * @param dtoList
     * @return
     */
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "deliveryOrders.do")
    protected Object deliveryOrders(@RequestBody List<OrdersDto> dtoList){
        if(null == dtoList || dtoList.isEmpty()){
            return new CommonResultDto().success();
        }
        ordersService.deliveryOrders(dtoList, this.getUserDetails());
        return new CommonResultDto().success();
    }
}
