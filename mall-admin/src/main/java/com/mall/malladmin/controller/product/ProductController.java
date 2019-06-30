package com.mall.malladmin.controller.product;

import com.mall.malladmin.entity.product.ProductEntity;
import com.mall.malladmin.service.product.ProductPropertyValueService;
import com.mall.malladmin.service.product.ProductService;
import com.mall.malladmin.util.ResultPage;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品信息
 */
@Slf4j
@RestController
@RequestMapping(value = "/productController")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;

    @Autowired
    private ProductPropertyValueService productPropertyValueService;
    /**
     * 获取商品分页信息
     * @param vo
     * @return
     */
    @GetMapping(value = "/getPage.do")
    protected CommonResultVo getPage(ProductVo vo){
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

    /**
     * 新建商品信息
     * @param vo
     * @return
     */
    @PostMapping(value = "/create.do")
    protected CommonResultVo create(@RequestBody ProductVo vo){
        return productService.create(vo);
    }
    /**
     * 根据商品名称获取商品列表
     * @return
     */
    @PostMapping(value = "/findProductByName.do")
    protected CommonResultVo findProductByName(String name){
        List<ProductVo> result = productService.findByName(name);
        return new CommonResultVo().success(result);
    }
    /**
     * 根据ID获取商品信息
     * @return
     */
    @GetMapping(value = "/getProduct.do/{id}")
    protected CommonResultVo getProduct(@PathVariable Integer id){
        if(null == id){
            return new CommonResultVo().validateFailed("id为空！");
        }
        ProductVo result = productService.findById(id);
        return new CommonResultVo().success(result);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping(value = "delete.do")
    protected CommonResultVo delete(Integer... ids){

        log.info("{}", ids);
        return new CommonResultVo().success();

    }

}