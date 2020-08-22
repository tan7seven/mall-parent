package com.mall.manage.controller.advert.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.AdvertTypeEnum;
import com.mall.common.model.vo.RestPage;
import com.mall.dao.entity.advert.AdvertEntity;
import com.mall.manage.model.vo.advert.AdvertVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/1
 */
public class AdvertUtil {

    public static RestPage<AdvertVO> buildAdvertVOList(Page<AdvertEntity> param){
        RestPage<AdvertVO> result = new RestPage<>(param.getCurrent(), param.getSize(), param.getTotal());
        if (CollectionUtils.isEmpty(param.getRecords())) {
            return result;
        }
        List<AdvertVO> voList = Lists.newArrayList();
        for (AdvertEntity advertEntity : param.getRecords()) {
            AdvertVO vo = new AdvertVO();
            BeanUtils.copyProperties(advertEntity, vo);
            vo.setTypeName(AdvertTypeEnum.getByCode(vo.getType()).getDesc());
            if (StringUtils.isNotBlank(advertEntity.getPicUrl())) {
                vo.setPicUrl(CommonConstant.IMG_PRE+advertEntity.getPicUrl());
            }
            voList.add(vo);
        }
        result.setRecords(voList);
        return result;
    }
}
