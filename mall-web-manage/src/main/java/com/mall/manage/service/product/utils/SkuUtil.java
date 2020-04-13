package com.mall.manage.service.product.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import com.mall.manage.model.vo.product.sku.SkuListVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/4/9
 */
public class SkuUtil {
    public static List<SkuListVO> setSkuListVO(List<ProductSkuEntity> skuEntityList, List<ProductAttrValueEntity> attrValueList){
        List<SkuListVO> result = Lists.newArrayList();
        for (ProductSkuEntity productSku : skuEntityList) {
            SkuListVO vo = new SkuListVO();
            BeanUtils.copyProperties(productSku, vo);
            if (CollectionUtils.isEmpty(attrValueList)) {
                continue;
            }
            List<AttrValueVO> attrList = Lists.newArrayList();
            /** 设置可选择的属性列表*/
            for (ProductAttrValueEntity valueEntity : attrValueList) {
                AttrValueVO attrValueVO = new AttrValueVO();
                BeanUtils.copyProperties(valueEntity, attrValueVO);
                /** 设置SKU已有的属性值*/
                if (StringUtils.isBlank(productSku.getAttrJson())) {
                    continue;
                }
                List<AttrValueVO> valueOldList = JSONObject.parseArray(productSku.getAttrJson(), AttrValueVO.class);
                for (AttrValueVO valueVOOld : valueOldList) {
                    if (valueVOOld.getNameId().equals(attrValueVO.getNameId())) {
                        attrValueVO.setSkuName(valueVOOld.getSkuName());
                        attrValueVO.setNameId(valueVOOld.getNameId());
                        attrValueVO.setSkuValue(valueVOOld.getSkuValue());
                        attrList.add(attrValueVO);
                    }
                }
            }
            vo.setAttrValueList(attrList);
            result.add(vo);
        }
        return result;
    }
}
