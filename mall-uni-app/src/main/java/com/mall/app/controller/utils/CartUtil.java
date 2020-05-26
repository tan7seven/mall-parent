package com.mall.app.controller.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.app.model.vo.cart.CartListVO;
import com.mall.app.model.vo.product.ProductAttrValueVO;
import com.mall.common.constant.CommonConstant;
import com.mall.common.model.vo.RestPage;
import com.mall.dao.entity.cart.CartEntity;
import com.mall.dao.entity.product.ProductSkuEntity;

import java.util.List;

public class CartUtil {

    public static RestPage<CartListVO> buildPageVO(Page<CartEntity> param, List<ProductSkuEntity> skuList){
        RestPage<CartListVO> result = new RestPage<>(param.getCurrent(), param.getSize(), param.getTotal());
        if (CollectionUtils.isEmpty(param.getRecords())) {
            return result;
        }
        List<CartListVO> listVO = Lists.newArrayList();
        for (CartEntity record : param.getRecords()) {
            CartListVO vo = buildVO(record, skuList);
            listVO.add(vo);
        }
        result.setRecords(listVO);
        return result;
    }

    public static CartListVO buildVO(CartEntity param,List<ProductSkuEntity> skuList){
        CartListVO result = new CartListVO();
        result.setId(param.getId());
        result.setNumber(param.getAmount());
        result.setTitle(param.getProductName());
        if (!CollectionUtils.isEmpty(skuList)) {
            for (ProductSkuEntity sku : skuList) {
                if (sku.getId().equals(param.getSkuId())) {
                    result.setImage(CommonConstant.IMG_PRE + sku.getPicUrl());
                    result.setPrice(sku.getSalePrice());
                    result.setStock(sku.getStock());
                    result.setSkuId(sku.getId());
                    try{
                        List<ProductAttrValueVO> attrValueVOList = JSONObject.parseArray(sku.getAttrJson(), ProductAttrValueVO.class);
                        StringBuffer attrVal = new StringBuffer();
                        for (ProductAttrValueVO valueVO : attrValueVOList) {
                            attrVal.append(valueVO.getSkuName()+ " : ");
                            attrVal.append(valueVO.getSkuValue()+ "  ");
                        }
                        result.setAttrVal(attrVal.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }
        return result;
    }
}
