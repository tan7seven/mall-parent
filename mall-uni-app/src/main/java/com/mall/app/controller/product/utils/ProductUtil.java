package com.mall.app.controller.product.utils;

import com.google.common.collect.Lists;
import com.mall.app.model.vo.product.ProductListVO;
import com.mall.app.model.vo.product.ProductTypeVO;
import com.mall.common.constant.CommonConstant;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductTypeEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ProductUtil {

    public static List<ProductTypeVO> buildTypeVO(List<ProductTypeEntity> typeList){
        List<ProductTypeVO> result = Lists.newArrayList();
        if (typeList.isEmpty()) {
            return result;
        }
        for (ProductTypeEntity typeEntity : typeList) {
            ProductTypeVO vo = new ProductTypeVO();
            vo.setParentId(typeEntity.getParentId());
            vo.setPicUrl(typeEntity.getTypePicUrl());
            vo.setTypeId(typeEntity.getId());
            vo.setTypeName(typeEntity.getTypeName());
            if (0L == typeEntity.getParentId()) {
                /** 如果是一级类目 需要过滤没有子级的类目*/
                for (ProductTypeEntity productTypeEntity : typeList) {
                    if (productTypeEntity.getParentId().equals(vo.getTypeId())) {
                        result.add(vo);
                        break;
                    }
                }
            }else{
                result.add(vo);
            }
        }
        return result;
    }

    public static List<ProductListVO> buildProductListVO(List<ProductEntity> productList){
        List<ProductListVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(productList)) {
            return result;
        }
        for (ProductEntity productEntity : productList) {
            ProductListVO vo = new ProductListVO();
            vo.setMinPrice(productEntity.getMinPrice());
            vo.setPicUrl(CommonConstant.IMG_PRE+productEntity.getPicUrl());
            vo.setProductId(productEntity.getId());
            vo.setProductName(productEntity.getProductName());
            result.add(vo);
        }
        return result;
    }
}
