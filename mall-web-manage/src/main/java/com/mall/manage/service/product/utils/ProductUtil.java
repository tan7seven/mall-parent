package com.mall.manage.service.product.utils;

import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.manage.model.param.product.product.CreateParam;
import com.mall.manage.model.vo.product.attr.AttrNameVO;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import com.mall.manage.model.vo.product.product.ProductDetailVO;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ProductUtil {
    public static ProductEntity buildCreateProductEntity(CreateParam param){
        ProductEntity result = new ProductEntity();
        result.setProductName(param.getProductName());
        result.setPicUrl(param.getPicUrl());
        result.setSort(param.getSort());
        result.setTypeId(param.getProductTypeId());
        result.setUnit(param.getUnit());
        result.setHits(0);
        result.setPutaway(Boolean.FALSE);
        result.setUsable(Boolean.FALSE);
        return result;
    }

    public static ProductDetailVO buildDetailProductVO(ProductEntity param){
        ProductDetailVO result = new ProductDetailVO();
        result.setPicUrl(CommonConstant.IMG_PRE + param.getPicUrl());
        result.setProductName(param.getProductName());
        result.setSort(param.getSort());
        result.setUnit(param.getUnit());
        result.setProductTypeId(param.getTypeId());
        return result;
    }

    public static void setDetailAttrValueList(List<ProductAttrValueEntity> param, ProductDetailVO result){
        if (CollectionUtils.isEmpty(param)) {
            return;
        }
        List<AttrValueVO> attrValueList = Lists.newArrayList();
        for (ProductAttrValueEntity valueEntity : param) {
            AttrValueVO attrValueVO = new AttrValueVO();
            attrValueVO.setNameId(valueEntity.getNameId());
            attrValueVO.setValue(valueEntity.getValue());
            attrValueList.add(attrValueVO);
        }
        result.setAttrValueVOList(attrValueList);
    }

    public static void setDetailAttrNameList(List<ProductAttrNameEntity> param, ProductDetailVO result){
        if (CollectionUtils.isEmpty(param)) {
            return;
        }
        List<AttrNameVO> attrNameVOList = Lists.newArrayList();
        for (ProductAttrNameEntity nameEntity : param) {
            AttrNameVO attrNameVO = new AttrNameVO();
            attrNameVO.setId(nameEntity.getId());
            attrNameVO.setInputData(nameEntity.getInputData());
            attrNameVO.setInputType(nameEntity.getInputType());
            attrNameVO.setName(nameEntity.getName());
            attrNameVOList.add(attrNameVO);
        }
        result.setAttrNameVOList(attrNameVOList);
    }
}
