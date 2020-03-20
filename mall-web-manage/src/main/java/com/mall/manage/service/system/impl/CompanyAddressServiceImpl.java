package com.mall.manage.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.order.CompanyAddressEntity;
import com.mall.dao.mapper.system.CompanyAddressMapper;
import com.mall.manage.service.system.CompanyAddressService;
import org.springframework.stereotype.Service;


@Service(value = "companyAddressService")
public class CompanyAddressServiceImpl extends ServiceImpl<CompanyAddressMapper, CompanyAddressEntity> implements CompanyAddressService {
}
