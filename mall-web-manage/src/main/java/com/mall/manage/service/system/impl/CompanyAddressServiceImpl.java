package com.mall.manage.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.order.CompanyAddressEntity;
import com.mall.dao.mapper.system.CompanyAddressMapper;
import com.mall.dao.repository.system.CompanyAddressRespository;
import com.mall.manage.service.system.CompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "companyAddressService")
public class CompanyAddressServiceImpl extends ServiceImpl<CompanyAddressMapper, CompanyAddressEntity> implements CompanyAddressService {

    @Autowired
    private CompanyAddressRespository companyAddressRespository;

    @Override
    public List<CompanyAddressEntity> getCompanyAddressList() {
        return companyAddressRespository.findAll();
    }
}
