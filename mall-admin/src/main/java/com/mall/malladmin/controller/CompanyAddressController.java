package com.mall.malladmin.controller;

import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.service.CompanyAddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 公司收货地址
 */
@RestController
@RequestMapping(value = "companyAddressController")
public class CompanyAddressController {

    @Resource(name = "companyAddressService")
    private CompanyAddressService companyAddressService;

    /**
     * 获取公司收货地址列表
     * @return
     */
    @PostMapping(value = "getCompanyAddressList.do")
    protected Object getCompanyAddressList(){
        return new CommonResultDto().success(companyAddressService.getCompanyAddressList());
    }
}
