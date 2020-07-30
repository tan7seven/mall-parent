package com.mall.app.controller.order.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.mall.app.model.BasePriceModel;
import com.mall.app.model.BaseSkuModel;
import com.mall.app.model.param.order.OrderCreateParam;
import com.mall.app.model.param.order.OrderSkuParam;
import com.mall.app.model.vo.order.CreateOrderVO;
import com.mall.app.model.vo.order.OrderPageVO;
import com.mall.app.model.vo.order.PayDetailVO;
import com.mall.app.model.vo.order.SkuVO;
import com.mall.app.model.vo.product.ProductAttrValueVO;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.OrderStatusEnum;
import com.mall.common.enums.PayTypeEnum;
import com.mall.common.utils.SnowflakeIdWorker;
import com.mall.dao.entity.order.OrderEntity;
import com.mall.dao.entity.order.OrderItemsEntity;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.entity.user.UserAddressEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/27
 */
public class OrderUtil {

    /**
     * 分页查询
     *
     * @return
     */
    public static List<OrderPageVO> buildPageVO(List<OrderEntity> orderList, List<OrderItemsEntity> itemList) {
        if (CollectionUtils.isEmpty(orderList)) {
            return null;
        }
        List<OrderPageVO> result = Lists.newArrayList();
        for (OrderEntity orderEntity : orderList) {
            OrderPageVO vo = new OrderPageVO();
            vo.setCreateTime(orderEntity.getCreateTime());
            vo.setOrderStatus(orderEntity.getOrderStatus());
            vo.setOrderStatusName(OrderStatusEnum.getByCode(orderEntity.getOrderStatus()).getDesc());
            List<OrderPageVO.OrderItem> items = Lists.newArrayList();
            for (OrderItemsEntity itemsEntity : itemList) {
                if (itemsEntity.getOrderId().equals(orderEntity.getId())) {
                    items.add(buildOrderPageItemVO(itemsEntity));
                }
            }
            vo.setItems(items);
            result.add(vo);
        }
        return result;
    }

    private static OrderPageVO.OrderItem buildOrderPageItemVO(OrderItemsEntity itemsEntity) {
        OrderPageVO.OrderItem result = new OrderPageVO.OrderItem();
        try {
            List<ProductAttrValueVO> attList = JSONObject.parseArray(itemsEntity.getSkuAttr(), ProductAttrValueVO.class);
            if (!CollectionUtils.isEmpty(attList)) {
                result.setAttr(attList.stream().map(s ->s.getSkuName() + ":" + s.getSkuValue()).collect(Collectors.joining("/n")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setPicUrl(itemsEntity.getPicUrl());
        result.setProductId(itemsEntity.getProductId());
        result.setProductName(itemsEntity.getProductName());
        result.setSkuAmount(itemsEntity.getSkuAmount());
        result.setSkuId(itemsEntity.getSkuId());
        result.setSkuPrice(itemsEntity.getSkuPrice());
        return result;
    }

    /**
     * 构建支付详情
     */
    public static PayDetailVO buildPayDetailVO(List<ProductSkuEntity> skuList,
                                               List<OrderSkuParam> skuParamList,
                                               List<ProductEntity> productList) {
        PayDetailVO result = new PayDetailVO();
        /** SKU信息*/
        List<SkuVO> skuVOList = Lists.newArrayList();
        for (ProductSkuEntity skuEntity : skuList) {
            for (ProductEntity productEntity : productList) {
                if (skuEntity.getProductId().equals(productEntity.getId())) {
                    SkuVO skuVo = buildSkuVO(productEntity, skuEntity);
                    skuVOList.add(skuVo);
                }
            }
        }
        /** 设置数量*/
        for (SkuVO skuVO : skuVOList) {
            for (OrderSkuParam skuParam : skuParamList) {
                if (skuVO.getSkuId().equals(skuParam.getSkuId())) {
                    skuVO.setAmount(skuParam.getAmount());
                }
            }
        }
        result.setSkuList(skuVOList);
        /** 费用信息*/
        PayDetailVO.PriceVO priceVO = result.new PriceVO();
        result.setPriceMsg(buildPrice(priceVO, skuVOList));
        return result;
    }

    /**
     * 构建sku信息
     */
    public static SkuVO buildSkuVO(ProductEntity productEntity, ProductSkuEntity skuEntity) {
        SkuVO result = new SkuVO();
        result.setPicUrl(CommonConstant.IMG_PRE + skuEntity.getPicUrl());
        result.setProductName(productEntity.getProductName());
        result.setSalePrice(skuEntity.getSalePrice());
        result.setSkuId(skuEntity.getId());
        try {
            List<ProductAttrValueVO> attrValueVOList = JSONObject.parseArray(skuEntity.getAttrJson(), ProductAttrValueVO.class);
            /** SKU属性*/
            StringBuffer attrVal = new StringBuffer();
            for (ProductAttrValueVO valueVO : attrValueVOList) {
                attrVal.append(valueVO.getSkuName() + " : ");
                attrVal.append(valueVO.getSkuValue() + "  ");
            }
            result.setAttrVal(attrVal.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 构建订单信息
     */
    public static OrderEntity buildOrder(List<ProductSkuEntity> skuList,
                                         OrderCreateParam createParam,
                                         UserAddressEntity addressEntity) {
        OrderEntity result = new OrderEntity();
        /** 获取订单价格*/
        BasePriceModel basePriceModel = new BasePriceModel();
        List<BaseSkuModel> skuModelList = Lists.newArrayList();
        for (ProductSkuEntity skuEntity : skuList) {
            for (OrderSkuParam skuParam : createParam.getSkuList()) {
                if (skuParam.getSkuId().equals(skuEntity.getId())) {
                    BaseSkuModel skuModel = new BaseSkuModel();
                    skuModel.setAmount(skuParam.getAmount());
                    skuModel.setSalePrice(skuEntity.getSalePrice());
                    skuModel.setSkuId(skuEntity.getId());
                    skuModelList.add(skuModel);
                }
            }
        }
        basePriceModel = buildPrice(basePriceModel, skuModelList);
        result.setCouponPrice(basePriceModel.getCouponPrice());
        result.setFreightPrice(basePriceModel.getFreightPrice());
        result.setPayPrice(basePriceModel.getPaidPrice());
        result.setTotalPrice(basePriceModel.getSkuPrice());
        /** 收货信息 */
        result.setReceiverName(addressEntity.getName());
        result.setReceiverPhone(addressEntity.getMobile());
        result.setReceiverProvince(addressEntity.getProvince());
        result.setReceiverCity(addressEntity.getCity());
        result.setReceiverRegion(addressEntity.getRegion());
        result.setReceiverDetailAddress(addressEntity.getAddress());
        /** 活动未做*/
        result.setPromotionPrice(BigDecimal.ZERO);
        /** 初始化订单基础信息 【交易单号、交易方式、订单状态、】*/
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(CommonConstant.SNOWFLAKE_WORKERID, CommonConstant.SNOWFLAKE_DATACENTERID);
        result.setOrderCode(snowflakeIdWorker.nextId());
        result.setPayType(PayTypeEnum.UN_PAY.getCode());
        result.setOrderStatus(OrderStatusEnum.UNPAID.getCode());
        return result;
    }

    /**
     * 构建订单明细列表
     */
    public static List<OrderItemsEntity> buildOrderItems(OrderEntity orderEntity, List<ProductSkuEntity> skuList, List<ProductEntity> productList, OrderCreateParam param) {
        List<OrderItemsEntity> result = Lists.newArrayList();
        for (ProductSkuEntity skuEntity : skuList) {
            OrderItemsEntity items = new OrderItemsEntity();
            // todo 金额拆分
            items.setCouponPrice(BigDecimal.ZERO);
            items.setRealPrice(skuEntity.getSalePrice());
            items.setSkuPrice(skuEntity.getSalePrice());
            items.setOrderId(orderEntity.getId());
            items.setPicUrl(skuEntity.getPicUrl());
            items.setProductId(skuEntity.getProductId());
            items.setSkuAttr(skuEntity.getAttrJson());
            items.setSkuId(skuEntity.getId());
            for (ProductEntity productEntity : productList) {
                if (productEntity.getId().equals(skuEntity.getProductId())) {
                    items.setProductName(productEntity.getProductName());
                }
            }
            for (OrderSkuParam skuParam : param.getSkuList()) {
                if (skuParam.getSkuId().equals(skuEntity.getId())) {
                    items.setSkuAmount(skuParam.getAmount());
                }
            }
            result.add(items);
        }
        return result;
    }


    /**
     * 新增订单参数
     */
    public static CreateOrderVO buildCreateOrderVO(OrderEntity orderEntity) {
        CreateOrderVO result = new CreateOrderVO();
        result.setOrderCode(orderEntity.getOrderCode());
        result.setPayPrice(orderEntity.getPayPrice());
        return result;
    }

    /**
     * 支付详情-收货地址信息
     */
    public static void buildPayDetaiAdressVO(PayDetailVO result, UserAddressEntity param) {
        PayDetailVO.ReceiverVO vo = result.new ReceiverVO();
        vo.setCity(param.getCity());
        vo.setAddress(param.getAddress());
        vo.setName(param.getName());
        vo.setMobile(param.getMobile());
        vo.setProvince(param.getProvince());
        vo.setRegion(param.getRegion());
        vo.setAddressId(param.getId());
        vo.setDefaulted(param.getDefaulted());
        result.setReceiverMsg(vo);
    }

    /**
     * 金额计算
     */
    public static <T extends BaseSkuModel, R extends BasePriceModel> R buildPrice(R priceModel, List<T> skuList) {
        BigDecimal skuPrice = skuList.stream().map(s -> s.getSalePrice().multiply(null == s.getAmount() ? BigDecimal.ONE : BigDecimal.valueOf(s.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        priceModel.setSkuPrice(skuPrice);
        // todo 优惠金额 运费
        BigDecimal couponPrice = BigDecimal.ZERO;
        priceModel.setCouponPrice(couponPrice);
        BigDecimal freightPrice = BigDecimal.ZERO;
        priceModel.setFreightPrice(freightPrice);
        BigDecimal paidPrice = skuPrice.add(freightPrice).subtract(couponPrice);
        priceModel.setPaidPrice(paidPrice);
        return priceModel;
    }
}
