package com.mall.app.service.product.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mall.app.model.vo.product.ProductAttrVO;
import com.mall.app.model.vo.product.ProductAttrValueVO;
import com.mall.app.model.vo.product.ProductDetailVO;
import com.mall.app.model.vo.product.ProductSkuVO;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.dao.entity.product.*;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductUtil {

    public static ProductDetailVO buildProductDetail(ProductEntity product,
                                                     List<ProductImgEntity> imgList,
                                                     List<ProductSkuEntity> skuList,
                                                     List<ProductAttrValueEntity> attrValueList,
                                                     ProductDetailEntity productDetail){
        ProductDetailVO result = new ProductDetailVO();
        result.setMinPrice(product.getMinPrice());
        result.setProductName(product.getProductName());
        result.setPutaway(product.getPutaway());
        result.setStatus(1);
        result.setUnit(product.getUnit());
        result.setUsable(product.getUsable());
        if (!CollectionUtils.isEmpty(imgList)) {
            result.setPicList(imgList.stream().map(s -> CommonConstant.IMG_PRE+s.getImgUrl()).collect(Collectors.toList()));
        }
        if (CollectionUtils.isEmpty(skuList)) {
            result.setStatus(2);
        }else{
            /** SKU列表*/
            List<ProductSkuVO> skuVOList = Lists.newArrayList();
            for (ProductSkuEntity skuEntity : skuList) {
                ProductSkuVO skuVO = new ProductSkuVO();
                skuVO.setAttrJson(skuEntity.getAttrJson());
                skuVO.setPicUrl(skuEntity.getPicUrl());
                skuVO.setSkuId(skuEntity.getId());
                skuVO.setStock(skuEntity.getStock());
                try{
                    List<ProductAttrValueVO> attrValueVOList = JSONObject.parseArray(skuEntity.getAttrJson(), ProductAttrValueVO.class);
                    skuVO.setAttrValueVO(attrValueVOList);
                }catch (Exception e){
                    e.printStackTrace();
                }
                skuVOList.add(skuVO);
            }
            result.setSkuList(skuVOList);
        }
        /** attr列表*/
       if (!CollectionUtils.isEmpty(attrValueList)) {
            List<ProductAttrVO> attrVOList = Lists.newArrayList();
           List<ProductAttrVO> showAttrVOList = Lists.newArrayList();
            for (ProductAttrValueEntity valueEntity : attrValueList) {
                ProductAttrVO attrVO = new ProductAttrVO();
                attrVO.setAttrName(valueEntity.getName());
                attrVO.setType(valueEntity.getType());
                attrVO.setNameId(valueEntity.getNameId());
                if (ProductAttrNameTypeEnum.SALE.getCode().equals(valueEntity.getType())) {
                    try{
                        List<String> attrStringList = JSONObject.parseArray(valueEntity.getValue(), String.class);
                        List<ProductAttrValueVO>  attrValueVOList = Lists.newArrayList();
                        for (String attrValue : attrStringList) {
                            ProductAttrValueVO attrValueVO = new ProductAttrValueVO();
                            attrValueVO.setNameId(valueEntity.getNameId());
                            attrValueVO.setSkuName(valueEntity.getName());
                            attrValueVO.setSkuValue(attrValue);
                            attrValueVOList.add(attrValueVO);
                        }
                        attrVO.setAttrValueList(attrValueVOList);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    attrVOList.add(attrVO);
                }else if(ProductAttrNameTypeEnum.NOT_SALE.getCode().equals(valueEntity.getType())){
                    attrVO.setShowValue(valueEntity.getValue());
                    showAttrVOList.add(attrVO);
                }

            }
            result.setAttrList(attrVOList);
            result.setShowAttrList(showAttrVOList);
        }
        /** 商品详情*/
        if (Objects.nonNull(productDetail)) {
            result.setDetail(productDetail.getDetail());
        }
        return result;
    }
}
