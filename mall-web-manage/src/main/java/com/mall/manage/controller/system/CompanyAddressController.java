package com.mall.manage.controller.system;

import com.mall.common.model.vo.RestResult;
import com.mall.manage.service.system.CompanyAddressService;
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
    public RestResult getCompanyAddressList(){
        return RestResult.success(companyAddressService.list());
    }
}
