package com.mall.app.controller.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.app.controller.cart.utils.CartUtil;
import com.mall.app.controller.order.utils.OrderUtil;
import com.mall.app.model.param.order.BuildPayDetailParam;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.vo.cart.CartListVO;
import com.mall.app.model.vo.order.CreateOrderVO;
import com.mall.app.model.vo.order.OrderPageVO;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.app.service.order.OrderItemsService;
import com.mall.app.service.order.OrderService;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.cart.CartEntity;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderItemsEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/25
 */

@Slf4j
@Api(tags = "订单模块")
@RestController
@RequestMapping(value = "/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemsService orderItemsService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/page")
    public RestResult<RestPage<OrderPageVO>> getPage(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                     @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize,
                                                     @ApiParam(value = "状态 0全部 1待付款 2待收货 3待评价 4售后") @RequestParam(defaultValue = "0") Integer status) {
        Long userId = 123L;
        Page pageParam = new Page(pageNum, pageSize, false);
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        List<Integer> orderStatusList = OrderStatusEnum.getByStatus(status);
        if (!CollectionUtils.isEmpty(orderStatusList)) {
            wrapper.in("order_status", orderStatusList);
        }
        Page<OrderEntity> orderPage = (Page<OrderEntity>) orderService.page(pageParam, wrapper);
        RestPage<OrderPageVO> result = new RestPage(orderPage.getCurrent(), orderPage.getSize(), orderPage.getTotal());
        if (CollectionUtils.isEmpty(orderPage.getRecords())) {
            return RestResult.success(result);
        }
        List<Long> orderIdList = orderPage.getRecords().stream().map(s -> s.getId()).collect(Collectors.toList());
        List<OrderItemsEntity> itemList = orderItemsService.list(Wrappers.<OrderItemsEntity>lambdaQuery()
                .in(OrderItemsEntity::getOrderId, orderIdList));
        List<OrderPageVO> voList = OrderUtil.buildPageVO(orderPage.getRecords(), itemList);
        result.setRecords(voList);
        return RestResult.success(result);
    }

    @ApiOperation(value = "支付详情")
    @PostMapping(value = "/build/pay-detail")
    public RestResult<PayDetailVO> buildPayDetail(@RequestBody @Validated BuildPayDetailParam param) {
        Long userId = 123L;
        PayDetailVO result = orderService.payDetail(param, userId);
        return RestResult.success(result);
    }

    @ApiOperation(value = "提交订单")
    @PutMapping(value = "/create")
    public RestResult<CreateOrderVO> create(@RequestBody @Validated OrderCreateParam param) {
        Long userId = 123L;
        CreateOrderVO result = orderService.createOrder(param, userId);
        return RestResult.success(result);
    }
}
