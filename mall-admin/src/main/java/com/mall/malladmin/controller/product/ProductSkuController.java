package com.mall.malladmin.controller.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.service.product.ProductSkuService;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductSkuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/productSkuController")
public class ProductSkuController extends GenericController {

    @Resource(name = "productSkuService")
    private ProductSkuService productSkuService;

    @RequestMapping(value = "/getPage.do")
    protected CommonResultVo getPage(ProductSkuVo vo){
        PageInfo<ProductSkuVo> result = productSkuService.findPage(vo);
        log.info("查询结果：{}", result);
        return new CommonResultVo().success();
    }
}
