package com.mall.mallwechar.controller.product;

import com.mall.mallmodel.dto.common.CommonResultDto;
import com.mall.mallmodel.dto.product.ProductTypeDto;
import com.mall.mallwechar.service.pruduct.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品信息
 */
@Slf4j
@RestController
@RequestMapping(value = "productController")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;

    /**
     * 获取所有类目信息
     * @param dto
     * @return
     */
    @RequestMapping(value = "getTypeList.do")
    protected  Object getTypeList(ProductTypeDto dto){
        return new CommonResultDto().success(productService.getTypeList(dto));
    }
}
