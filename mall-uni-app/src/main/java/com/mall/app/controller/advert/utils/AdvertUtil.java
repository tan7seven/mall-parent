package com.mall.app.controller.advert.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.mall.app.model.vo.advert.AdvertCarouseVO;
import com.mall.app.model.vo.advert.AdvertCateVO;
import com.mall.app.model.vo.advert.AdvertProductVO;
import com.mall.app.model.vo.advert.AdvertVO;
import com.mall.common.constant.CommonConstant;
import com.mall.dao.entity.advert.AdvertEntity;
import com.mall.dao.entity.advert.AdvertProductEntity;

import java.util.List;

public class AdvertUtil {
    /**
     * 广告列表-分页
     */
    public static List<AdvertVO> buildAdvertVO(List<AdvertEntity> advertList, List<AdvertProductEntity> advertProductList) {
        List<AdvertVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(advertList)) {
            return result;
        }
        for (AdvertEntity advertEntity : advertList) {
            AdvertVO vo = new AdvertVO();
            vo.setBackColor(advertEntity.getBackColor());
            vo.setSkipUrl(advertEntity.getSkipUrl());
            vo.setSort(advertEntity.getSort());
            vo.setPicUrl(CommonConstant.IMG_PRE + advertEntity.getPicUrl());
            vo.setType(advertEntity.getType());
            vo.setTitle(advertEntity.getTitle());
            List<AdvertProductVO> productVOList = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(advertProductList)) {
                for (AdvertProductEntity advertProductEntity : advertProductList) {
                    if (advertProductEntity.getAdvertId().equals(advertEntity.getId())) {
                        AdvertProductVO productVO = new AdvertProductVO();
                        productVO.setAdvertId(advertProductEntity.getAdvertId());
                        productVO.setPicUrl(CommonConstant.IMG_PRE + advertProductEntity.getPicUrl());
                        productVO.setPrice(advertProductEntity.getPrice());
                        productVO.setProductId(advertProductEntity.getProductId());
                        productVO.setProductName(advertProductEntity.getProductName());
                        productVOList.add(productVO);
                    }
                }
            }
            vo.setProductList(productVOList);
            result.add(vo);
        }
        return result;
    }

    /**
     * 广告列表-旋转木马
     */
    public static List<AdvertCarouseVO> buildAdvertCarouseVO(List<AdvertEntity> advertList) {
        List<AdvertCarouseVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(advertList)) {
            return result;
        }
        for (AdvertEntity advertEntity : advertList) {
            AdvertCarouseVO vo = new AdvertCarouseVO();
            vo.setBackColor(advertEntity.getBackColor());
            vo.setSkipUrl(advertEntity.getSkipUrl());
            vo.setSort(advertEntity.getSort());
            vo.setTitle(advertEntity.getTitle());
            vo.setPicUrl(CommonConstant.IMG_PRE + advertEntity.getPicUrl());
            result.add(vo);
        }
        return result;
    }


    /**
     * 广告列表-分类
     */
    public static List<AdvertCateVO> buildAdvertCateVO(List<AdvertEntity> advertList) {
        List<AdvertCateVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(advertList)) {
            return result;
        }
        for (AdvertEntity advertEntity : advertList) {
            AdvertCateVO vo = new AdvertCateVO();
            vo.setBackColor(advertEntity.getBackColor());
            vo.setSkipUrl(advertEntity.getSkipUrl());
            vo.setSort(advertEntity.getSort());
            vo.setTitle(advertEntity.getTitle());
            vo.setPicUrl(CommonConstant.IMG_PRE + advertEntity.getPicUrl());
            result.add(vo);
        }
        return result;
    }
}
