package com.mall.manage.controller.advert.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.AdvertTypeEnum;
import com.mall.common.model.vo.RestPage;
import com.mall.dao.entity.advert.AdvertEntity;
import com.mall.dao.entity.advert.AdvertProductEntity;
import com.mall.manage.model.vo.advert.AdvertProductVO;
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
public class AdvertProductUtil {

    public static RestPage<AdvertProductVO> buildAdvertProductVOList(Page<AdvertProductEntity> param){
        RestPage<AdvertProductVO> result = new RestPage<>(param.getCurrent(), param.getSize(), param.getTotal());
        if (CollectionUtils.isEmpty(param.getRecords())) {
            return result;
        }
        List<AdvertProductVO> voList = Lists.newArrayList();
        for (AdvertProductEntity entity : param.getRecords()) {
            AdvertProductVO vo = new AdvertProductVO();
            BeanUtils.copyProperties(entity, vo);
            voList.add(vo);
        }
        result.setRecords(voList);
        return result;
    }
}
