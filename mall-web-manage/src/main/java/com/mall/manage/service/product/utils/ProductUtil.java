package com.mall.manage.service.product.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.AttrNameInputTypeEnum;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.manage.model.param.product.product.CreateParam;
import com.mall.manage.model.param.product.product.UpdateParam;
import com.mall.manage.model.vo.product.attr.AttrNameVO;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import com.mall.manage.model.vo.product.product.ProductDetailVO;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductUtil {
    public static ProductEntity buildCreateProductEntity(CreateParam param) {
        ProductEntity result = new ProductEntity();
        result.setProductName(param.getProductName());
        result.setPicUrl(param.getPicUrl().replaceAll(CommonConstant.IMG_PRE, ""));
        result.setSort(param.getSort());
        result.setTypeId(param.getProductTypeId());
        result.setUnit(param.getUnit());
        result.setHits(0);
        result.setPutaway(Boolean.FALSE);
        result.setUsable(Boolean.FALSE);
        return result;
    }

    public static ProductEntity buildUpdateProductEntity(UpdateParam param) {
        ProductEntity result = new ProductEntity();
        result.setId(param.getId());
        result.setProductName(param.getProductName());
        result.setPicUrl(param.getPicUrl().replaceAll(CommonConstant.IMG_PRE, ""));
        result.setSort(param.getSort());
        result.setUnit(param.getUnit());
        return result;
    }

    public static ProductDetailVO buildDetailProductVO(ProductEntity param) {
        ProductDetailVO result = new ProductDetailVO();
        result.setPicUrl(CommonConstant.IMG_PRE + param.getPicUrl());
        result.setProductName(param.getProductName());
        result.setSort(param.getSort());
        result.setUnit(param.getUnit());
        result.setProductTypeId(param.getTypeId());
        result.setAttrTypeId(param.getAttrTypeId());
        return result;
    }

    public static void setDetailAttrValueList(List<ProductAttrValueEntity> param, List<ProductAttrNameEntity> attrNameList, ProductDetailVO result) {
        if (CollectionUtils.isEmpty(param)) {
            return;
        }
        if (CollectionUtils.isEmpty(attrNameList)) {
            return;
        }
        List<AttrValueVO> attrValueList = Lists.newArrayList();
        for (ProductAttrValueEntity valueEntity : param) {
            AttrValueVO attrValueVO = new AttrValueVO();
            List<ProductAttrNameEntity> nameEntityList = attrNameList.stream().filter(s -> s.getId().equals(valueEntity.getNameId())).limit(1).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(nameEntityList)) {
                continue;
            }
            ProductAttrNameEntity nameEntity = nameEntityList.get(0);
            if (AttrNameInputTypeEnum.HAND.getCode().equals(nameEntity.getInputType())) {
                attrValueVO.setValue(valueEntity.getValue());
            } else {
                try {
                    List<String> attrStringList = JSONObject.parseArray(valueEntity.getValue(), String.class);
                    attrValueVO.setValue(attrStringList);
                } catch (Exception e) {
                    attrValueVO.setValue(valueEntity.getValue());
                }
            }
            attrValueVO.setInputType(nameEntity.getInputType());
            attrValueVO.setId(valueEntity.getId());
            attrValueVO.setNameId(valueEntity.getNameId());
            attrValueList.add(attrValueVO);
        }
        attrValueList = attrValueList.stream().sorted(Comparator.comparing(AttrValueVO::getInputType).reversed()).collect(Collectors.toList());
        result.setAttrValueVOList(attrValueList);
    }

    public static void setDetailAttrNameList(List<ProductAttrNameEntity> param, ProductDetailVO result) {
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
