package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.controller.order.utils.OrderUtil;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.vo.order.CreateOrderVO;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.app.service.cart.CartService;
import com.mall.app.service.order.OrderItemsService;
import com.mall.app.service.order.OrderService;
import com.mall.app.service.product.ProductService;
import com.mall.app.service.product.ProductSkuService;
import com.mall.common.exception.BusinessException;
import com.mall.dao.entity.cart.CartEntity;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderItemsEntity;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.mapper.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemsService orderItemsService;

    @Override
    public PayDetailVO payDetail(List<Long> skuIdList, Long userId) {
        /** 获取SKU列表*/
        List<ProductSkuEntity> skuList = productSkuService.list(Wrappers.<ProductSkuEntity>lambdaQuery()
                .in(ProductSkuEntity::getId, skuIdList));
        if (CollectionUtils.isEmpty(skuList)) {
            throw new BusinessException("无效商品");
        }
        List<Long> productIdList = skuList.stream().map(s -> s.getProductId()).collect(Collectors.toList());
        List<ProductEntity> productList = productService.list(Wrappers.<ProductEntity>lambdaQuery().in(ProductEntity::getId, productIdList));

        List<CartEntity> cartList = cartService.list(Wrappers.<CartEntity>lambdaQuery()
                .in(CartEntity::getSkuId, skuIdList)
                .eq(CartEntity::getUserId, userId));
        // todo
        /** 获取营销列表（未做）*/
        // todo
        /** 获取用户信息 收货地址（未做）*/
        PayDetailVO result = OrderUtil.buildPayDetailVO(skuList, cartList, productList);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateOrderVO createOrder(OrderCreateParam param, Long userId) {
        /** 获取SKU列表*/
        List<Long> skuIdList = param.getSkuList().stream().map(s -> s.getSkuId()).collect(Collectors.toList());
        List<ProductSkuEntity> skuList = productSkuService.list(Wrappers.<ProductSkuEntity>lambdaQuery()
                .in(ProductSkuEntity::getId, skuIdList));
        if (CollectionUtils.isEmpty(skuList)) {
            throw new BusinessException("无效商品");
        }
        List<Long> productIdList = skuList.stream().map(s -> s.getProductId()).collect(Collectors.toList());
        List<ProductEntity> productList = productService.list(Wrappers.<ProductEntity>lambdaQuery().in(ProductEntity::getId, productIdList));
        OrderEntity orderEntity = OrderUtil.buildOrder(skuList, param);
        orderEntity.setUserId(userId);
        orderEntity.setOrderRemark(param.getRemark());
        Boolean saveOrder = this.save(orderEntity);
        if (!saveOrder) {
            throw new BusinessException("保持订单失败");
        }
        List<OrderItemsEntity> itemsList = OrderUtil.buildOrderItems(orderEntity, skuList, productList, param);
        Boolean saveOrderItems = orderItemsService.saveBatch(itemsList);
        if (!saveOrderItems) {
            throw new BusinessException("保持订单明细失败");
        }
        /** 删除购物车信息*/
        cartService.remove(Wrappers.<CartEntity>lambdaQuery()
                .in(CartEntity::getSkuId, skuIdList)
                .eq(CartEntity::getUserId, userId));
        /** 构建返回信息 */
        CreateOrderVO result = OrderUtil.buildCreateOrderVO(orderEntity);
        return result;
    }
}
