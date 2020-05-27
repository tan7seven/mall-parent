package com.mall.app.controller.product.utils;

import com.google.common.collect.Lists;
import com.mall.app.model.vo.product.ProductTypeVO;
import com.mall.dao.entity.product.ProductTypeEntity;

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
            result.add(vo);
        }
        return result;
    }

}
