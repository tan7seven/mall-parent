package com.mall.app.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.app.model.param.user.AddressParam;
import com.mall.dao.entity.user.UserAddressEntity;

public interface UserAddressService  extends IService<UserAddressEntity> {

    /**
     * 添加收货地址
     * @param param
     * @param userId
     * @return
     */
    Boolean addAddress(AddressParam param, Long userId);

    /**
     * 修改收货地址
     * @param param
     * @param userId
     * @return
     */
    Boolean modifyAddress(AddressParam param, Long userId);
}
