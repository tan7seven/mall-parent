package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductEntity;
import com.mall.malladmin.service.product.ProductService;
import com.mall.malladmin.util.ResultPage;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品信息
 */
@Slf4j
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;
    /**
     * 获取商品分页信息
     * @param vo
     * @return
     */
    @GetMapping(value = "/getPage.do")
    protected CommonResultVo getPage(ProductVo vo){
        log.info("接收vo:{}", vo);
        Sort sort = new Sort(Sort.Direction.ASC, "addTime", "productId");
        Pageable page = PageRequest.of(vo.getPageNum()-1, vo.getPageSize(), sort);
        ProductEntity entity = new ProductEntity();
        if(StringUtils.isNotBlank(vo.getProductName())){
            entity.setProductName(vo.getProductName());
        }
        if(null != vo.getProductId()){
            entity.setProductId(vo.getProductId());
        }
        if(StringUtils.isNotBlank(vo.getStatus())){
            entity.setStatus(vo.getStatus());
        }
        if(null != vo.getTypeId()){
            entity.setTypeId(vo.getTypeId());
        }
        Page<ProductEntity> result = productService.findPage(entity, page);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultVo().success(resultPage);
    }

}
