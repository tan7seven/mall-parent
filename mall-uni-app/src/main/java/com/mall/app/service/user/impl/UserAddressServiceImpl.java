package com.mall.app.service.user.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.model.param.user.AddressParam;
import com.mall.app.service.user.UserAddressService;
import com.mall.dao.entity.user.UserAddressEntity;
import com.mall.dao.mapper.user.UserAddressMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressEntity> implements UserAddressService {
    @Override
    public Boolean addAddress(AddressParam param, Long userId) {
        if(param.getDefaulted()){
            UserAddressEntity updateParam = new UserAddressEntity();
            updateParam.setDefaulted(Boolean.FALSE);
            this.update(updateParam, Wrappers.<UserAddressEntity>lambdaQuery().eq(UserAddressEntity::getUserId, userId));
        }
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        BeanUtils.copyProperties(param, userAddressEntity);
        userAddressEntity.setUserId(userId);
        userAddressEntity.setUsable(Boolean.TRUE);
        Boolean result = this.save(userAddressEntity);
        return result;
    }

    @Override
    public Boolean modifyAddress(AddressParam param, Long userId) {
        if(param.getDefaulted()){
            UserAddressEntity updateParam = new UserAddressEntity();
            updateParam.setDefaulted(Boolean.FALSE);
            this.update(updateParam, Wrappers.<UserAddressEntity>lambdaQuery().eq(UserAddressEntity::getUserId, userId));
        }
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        BeanUtils.copyProperties(param, userAddressEntity);
        userAddressEntity.setId(param.getId());
        Boolean result = this.updateById(userAddressEntity);
        return result;
    }
}
