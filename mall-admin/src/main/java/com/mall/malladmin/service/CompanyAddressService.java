package com.mall.malladmin.service;

import com.mall.malladmin.entity.order.CompanyAddressEntity;

import java.util.List;

/**
 * 公司收货地址
 */
public interface CompanyAddressService {
    /**
     * 获取所有列表
     * @return
     */
    List<CompanyAddressEntity> getCompanyAddressList();
}
