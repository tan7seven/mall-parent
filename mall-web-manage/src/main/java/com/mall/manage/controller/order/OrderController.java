package com.mall.manage.controller.order;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.order.OrderDTO;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderOperationLogEntity;
import com.mall.dao.entity.product.ProductAttrTypeEntity;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.controller.order.util.OrderOperationLogUtil;
import com.mall.manage.controller.order.util.OrderUtil;
import com.mall.manage.model.param.order.OrderUpdateReceiverInfoParam;
import com.mall.manage.model.param.order.OrderUpdateRemarkParam;
import com.mall.manage.model.vo.order.OrderDetailVO;
import com.mall.manage.model.vo.order.OrderPageVO;
import com.mall.manage.service.order.OrderOperationLogService;
import com.mall.manage.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Api(value = "订单信息", tags = "订单信息")
@RestController
@RequestMapping(value = "/order")
public class OrderController extends GenericController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @Autowired
    private OrderOperationLogService orderOperationLogService;

    @ApiOperation("分页查询")
    @GetMapping(value = "/page")
    protected RestResult<RestPage<OrderPageVO>> getPage(@ApiParam(value = "收货人姓名") @RequestParam(required = false) String receiverName,
                                                        @ApiParam(value = "创建时间") @RequestParam(required = false) String createTime,
                                                        @ApiParam(value = "订单状态") @RequestParam(required = false) Integer orderStatus,
                                                        @ApiParam(value = "支付方式") @RequestParam(required = false) Integer payType,
                                                        @ApiParam(value = "订单编号") @RequestParam(required = false) String orderCode,
                                                        @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                        @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize) {
        Page pageParam = new Page(pageNum, pageSize);
        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper();
        wrapper.like(StringUtils.isNoneBlank(receiverName), OrderEntity::getReceiverName, receiverName)
                .likeRight(StringUtils.isNoneBlank(createTime), OrderEntity::getCreateTime, createTime)
                .eq(Objects.nonNull(orderStatus), OrderEntity::getOrderStatus, orderStatus)
                .eq(Objects.nonNull(payType), OrderEntity::getPayType, payType)
                .eq(StringUtils.isNoneBlank(orderCode), OrderEntity::getOrderCode, orderCode);
        Page<OrderEntity> orderPage = (Page<OrderEntity>) orderService.page(pageParam, wrapper);
        RestPage<OrderPageVO> result = new RestPage<>(orderPage.getCurrent(), orderPage.getSize(), orderPage.getTotal());
        List<OrderPageVO> voList = OrderUtil.buildPageVO(orderPage.getRecords());
        result.setRecords(voList);
        return RestResult.success(result);
    }

    @ApiOperation("订单明细")
    @GetMapping(value = "/detail/{id}")
    public RestResult<OrderDetailVO> detail(@NotNull(message = "订单号不能为空") @PathVariable Long id) {
        OrderDetailVO result = orderService.getOrderDetail(id);
        return RestResult.success(result);
    }

    @ApiOperation("修改订单备注")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/remark-update")
    protected RestResult<Boolean> updateRemark(@Validated @RequestBody OrderUpdateRemarkParam param) {
        OrderEntity orderEntity = orderService.getById(param.getOrderId());
        if (Objects.isNull(orderEntity)) {
            throw new BusinessException("订单不存在");
        }
        OrderEntity updateParam = new OrderEntity();
        updateParam.setId(orderEntity.getId());
        updateParam.setOrderRemark(param.getRemark());
        boolean result = orderService.updateById(updateParam);
        if (result) {
            OrderOperationLogEntity logEntity =
                    OrderOperationLogUtil.buildOrderOperationLog(orderEntity, param.getRemark(), super.getUserDetails().getUserId());
            orderOperationLogService.save(logEntity);
        }
        return RestResult.success(result);
    }

    @ApiOperation("修改订单收货信息")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/receiver-update")
    protected RestResult updateReceiverInfo(@Validated @RequestBody OrderUpdateReceiverInfoParam param) {
        OrderEntity orderEntity = orderService.getById(param.getOrderId());
        if (Objects.isNull(orderEntity)) {
            throw new BusinessException("订单不存在");
        }
        OrderEntity updateParam = new OrderEntity();
        updateParam.setId(orderEntity.getId());
        updateParam.setReceiverDetailAddress(param.getReceiverDetailAddress());
        updateParam.setReceiverRegion(param.getReceiverRegion());
        updateParam.setReceiverCity(param.getReceiverCity());
        updateParam.setReceiverProvince(param.getReceiverProvince());
        updateParam.setReceiverPhone(param.getReceiverPhone());
        updateParam.setReceiverName(param.getReceiverName());
        boolean result = orderService.updateById(updateParam);
        if (result) {
            OrderOperationLogEntity logEntity =
                    OrderOperationLogUtil.buildOrderOperationLog(orderEntity, "修改收货信息", super.getUserDetails().getUserId());
            orderOperationLogService.save(logEntity);
        }
        return RestResult.success(result);
    }
    // todo done

    @ApiOperation("修改订单金额")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateMoneyInfo.do")
    protected RestResult updateMoneyInfo(@RequestBody OrderDTO dto) {
        orderService.updateMoneyInfo(dto, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("关闭订单 -> 手动关闭的订单为无效订单")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "closeOrder.do")
    protected RestResult closeOrder(OrderDTO dto) {
        orderService.closeOrder(dto, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("关闭订单 -> 手动关闭的订单为无效订单")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "closeOrderList.do")
    protected RestResult closeOrderList(List<String> ids, String remark) {
        orderService.closeOrderList(ids, remark, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("删除订单信息")
    @PreAuthorize(" hasAuthority('OMS:ORDER:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteOrder.do")
    protected RestResult deleteOrder(List<String> ids) {
        orderService.deleteOrder(ids, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("批量发货")
    @PreAuthorize(" hasAuthority('OMS:ORDER:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "deliveryOrder.do")
    protected RestResult deliveryOrder(@RequestBody List<OrderDTO> dtoList) {
        orderService.deliveryOrder(dtoList, this.getUserDetails());
        return RestResult.success();
    }
}
