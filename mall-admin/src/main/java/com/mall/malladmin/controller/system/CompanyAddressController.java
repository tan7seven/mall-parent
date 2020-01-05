package com.mall.malladmin.controller.system;

import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.service.CompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(value = "公司收货地址", tags = "公司收货地址")
@RestController
@RequestMapping(value = "companyAddressController")
public class CompanyAddressController {

    @Resource(name = "companyAddressService")
    private CompanyAddressService companyAddressService;

    @ApiOperation("获取公司收货地址列表")
    @PostMapping(value = "getCompanyAddressList.do")
    protected CommonResultDto getCompanyAddressList(){
        return new CommonResultDto().success(companyAddressService.getCompanyAddressList());
    }
}
