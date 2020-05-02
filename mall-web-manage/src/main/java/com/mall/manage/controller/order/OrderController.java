package com.mall.manage.controller.order;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.order.OrderDTO;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Api(value = "订单信息", tags = "订单信息")
@RestController
@RequestMapping(value = "/orderController")
public class OrderController extends GenericController{

    @Resource(name = "orderService")
    private OrderService orderService;

    @ApiOperation("分页查询")
    @PostMapping(value = "getPage.do")
    protected RestResult<Page<OrderDTO>> getPage(@RequestBody OrderDTO dto){
        Page<OrderDTO> result = orderService.getPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("订单明细")
    @GetMapping(value = "getOrder.do/{id}")
    protected RestResult getOrder(@PathVariable String id){
        if(null == id || "undefined".equals(id)){
            return RestResult.validateFailed("ID为空！");
        }
        OrderDTO result = orderService.getOrderById(id);
        return RestResult.success(result);
    }

    @ApiOperation("修改订单收货信息")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateReceiverInfo.do")
    protected RestResult updateReceiverInfo(@RequestBody OrderDTO dto){
        orderService.updateReceiverInfo(dto, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("修改订单金额")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateMoneyInfo.do")
    protected RestResult updateMoneyInfo(@RequestBody OrderDTO dto){
        if(null == dto.getDiscountPrice()){
            dto.setDiscountPrice(new BigDecimal(0));
        }
        orderService.updateMoneyInfo(dto, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("修改订单备注")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateRemarkInfo.do")
    protected RestResult updateRemarkInfo(OrderDTO dto){
        orderService.updateRemarkInfo(dto, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("关闭订单 -> 手动关闭的订单为无效订单")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "closeOrder.do")
    protected RestResult closeOrder(OrderDTO dto){
        orderService.closeOrder(dto, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("关闭订单 -> 手动关闭的订单为无效订单")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "closeOrderList.do")
    protected RestResult closeOrderList(List<String> ids, String remark){
        if (null ==ids || StringUtils.isBlank(ids.toString())) {
            return RestResult.success();
        }
        orderService.closeOrderList(ids, remark, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("删除订单信息")
    @PreAuthorize(" hasAuthority('OMS:ORDER:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteOrder.do")
    protected RestResult deleteOrder(List<String> ids){
        if (StringUtils.isBlank(ids.toString())) {
            return RestResult.validateFailed("参数为空！");
        }
        orderService.deleteOrder(ids, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("批量发货")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "deliveryOrder.do")
    protected RestResult deliveryOrder(@RequestBody List<OrderDTO> dtoList){
        if(null == dtoList || dtoList.isEmpty()){
            return RestResult.success();
        }
        orderService.deliveryOrder(dtoList, this.getUserDetails());
        return RestResult.success();
    }
}
