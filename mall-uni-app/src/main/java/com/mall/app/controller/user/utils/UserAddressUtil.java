package com.mall.app.controller.user.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.app.model.vo.user.AddressVO;
import com.mall.common.model.vo.RestPage;
import com.mall.dao.entity.user.UserAddressEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class UserAddressUtil {
    public static RestPage<AddressVO> buildListVO(Page<UserAddressEntity> param){
        RestPage<AddressVO> result = new RestPage(param.getCurrent(), param.getSize(), param.getTotal());
        List<AddressVO> voList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(param.getRecords())) {
            return result;
        }
        for (UserAddressEntity record : param.getRecords()) {
            AddressVO vo = new AddressVO();
            BeanUtils.copyProperties(record, vo);
            voList.add(vo);
        }
        result.setRecords(voList);
        return result;
    }
}
