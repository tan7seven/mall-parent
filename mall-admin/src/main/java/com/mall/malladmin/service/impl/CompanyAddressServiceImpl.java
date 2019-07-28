package com.mall.malladmin.service.impl;

import com.mall.malladmin.entity.CompanyAddressEntity;
import com.mall.malladmin.repository.CompanyAddressRespository;
import com.mall.malladmin.service.CompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "companyAddressService")
public class CompanyAddressServiceImpl implements CompanyAddressService {

    @Autowired
    private CompanyAddressRespository companyAddressRespository;

    @Override
    public List<CompanyAddressEntity> getCompanyAddressList() {
        return companyAddressRespository.findAll();
    }
}
