package com.mall.manage.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.entity.order.CompanyAddressEntity;

import java.util.List;

/**
 * 公司收货地址
 */
public interface CompanyAddressService extends IService<CompanyAddressEntity> {
    /**
     * 获取所有列表
     * @return
     */
    List<CompanyAddressEntity> getCompanyAddressList();
}
