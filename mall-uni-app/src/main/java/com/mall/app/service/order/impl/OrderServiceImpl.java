package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.controller.order.utils.OrderUtil;
import com.mall.app.model.param.order.BuildPayDetailParam;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.param.order.WXOrderPayNoticeParam;
import com.mall.app.model.vo.order.CreateOrderVO;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.app.service.cart.CartService;
import com.mall.app.service.order.OrderItemsService;
import com.mall.app.service.order.OrderService;
import com.mall.app.service.product.ProductService;
import com.mall.app.service.product.ProductSkuService;
import com.mall.app.service.user.UserAddressService;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.common.exception.BusinessException;
import com.mall.common.utils.DateUtil;
import com.mall.dao.entity.cart.CartEntity;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderItemEntity;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.entity.user.UserAddressEntity;
import com.mall.dao.mapper.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    @Autowired
    private UserAddressService userAddressService;

    @Override
    public PayDetailVO payDetail(BuildPayDetailParam param, Long userId) {
        List<Long> skuIdList = param.getSkuList().stream().map(s -> s.getSkuId()).collect(Collectors.toList());
        /** 获取SKU列表*/
        List<ProductSkuEntity> skuList = productSkuService.list(Wrappers.<ProductSkuEntity>lambdaQuery()
                .in(ProductSkuEntity::getId, skuIdList));
        if (CollectionUtils.isEmpty(skuList)) {
            throw new BusinessException("无效商品");
        }
        List<Long> productIdList = skuList.stream().map(s -> s.getProductId()).collect(Collectors.toList());
        List<ProductEntity> productList = productService.list(Wrappers.<ProductEntity>lambdaQuery().in(ProductEntity::getId, productIdList));
        /** 构建SKU，商品信息*/
        PayDetailVO result = OrderUtil.buildPayDetailVO(skuList, param.getSkuList(), productList);

        // todo 获取营销列表（未做）

        /** 收货信息*/
        List<UserAddressEntity> addressList = userAddressService.list(Wrappers.<UserAddressEntity>lambdaQuery()
                .eq(UserAddressEntity::getUserId, userId)
                .orderByAsc(UserAddressEntity::getDefaulted));
        if (!CollectionUtils.isEmpty(addressList)) {
            /** 构建收货地址信息*/
            OrderUtil.buildPayDetailAddressVO(result, addressList.get(0));
        }
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

        if (skuList.size() != skuIdList.size()) {
            throw new BusinessException("存在无效商品，请重新选择下单");
        }
        /** 商品信息 */
        List<Long> productIdList = skuList.stream().map(s -> s.getProductId()).collect(Collectors.toList());
        List<ProductEntity> productList = productService.list(Wrappers.<ProductEntity>lambdaQuery().in(ProductEntity::getId, productIdList));

        if (CollectionUtils.isEmpty(productList)) {
            throw new BusinessException("无效商品");
        }

        /** 收货信息 */
        UserAddressEntity addressEntity = userAddressService.getById(param.getAddressId());
        if (Objects.isNull(addressEntity)) {
            throw new BusinessException("无效的收货信息");
        }

        /** 构建订单主表 */
        OrderEntity orderEntity = OrderUtil.buildOrder(skuList, param, addressEntity);
        orderEntity.setUserId(userId);
        Boolean saveOrder = this.save(orderEntity);
        if (!saveOrder) {
            throw new BusinessException("保持订单失败");
        }

        /** 构建订单明细 */
        List<OrderItemEntity> itemList = OrderUtil.buildOrderItem(orderEntity, skuList, productList, param);
        Boolean saveOrderItems = orderItemsService.saveBatch(itemList);
        if (!saveOrderItems) {
            throw new BusinessException("保持订单明细失败");
        }
        /** 删除购物车信息*/
        cartService.remove(Wrappers.<CartEntity>lambdaQuery()
                .in(CartEntity::getSkuId, skuIdList)
                .eq(CartEntity::getUserId, userId));
        // todo 扣减库存 营销活动 订单变更MQ
        
        /** 构建返回信息 */
        CreateOrderVO result = OrderUtil.buildCreateOrderVO(orderEntity);
        return result;
    }

    @Override
    public Boolean wxOrderPayNotice(WXOrderPayNoticeParam param) {
        OrderEntity orderEntity = this.getById(param.getOutTradeNo());
        /** 不是待支付订单 */
        if (!OrderStatusEnum.UNPAID.getCode().equals(orderEntity.getOrderStatus())) {
            return Boolean.FALSE;
        }
        // todo 签名验证
        OrderEntity updateEntity = new OrderEntity();
        /** 实际支付金额跟订单不同*/
        updateEntity.setId(orderEntity.getId());
        updateEntity.setRealTotalPrice(param.getTotalFee());
        updateEntity.setPaymentTime(DateUtil.format(DateUtil.YYYY_MM_DD_HH_MM_SS_TIGHT, param.getTimeEnd()));
        updateEntity.setTransactionId(param.getTransactionId());
        if (!orderEntity.getTotalPrice().equals(param.getTotalFee())) {
            updateEntity.setOrderStatus(OrderStatusEnum.ERROR_PAY_PRICE.getCode());
        }else{
            updateEntity.setOrderStatus(OrderStatusEnum.PAID_WAIT_DELIVER.getCode());
        }
        this.updateById(updateEntity);
        return Boolean.TRUE;
    }
}
