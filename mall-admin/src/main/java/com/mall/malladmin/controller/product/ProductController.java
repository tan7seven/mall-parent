package com.mall.malladmin.controller.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.service.product.ProductPropertyValueService;
import com.mall.malladmin.service.product.ProductService;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        PageInfo<ProductVo> result = productService.findPage(vo);
        return new CommonResultVo().success(result);
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
     * 更新
     * @return
     */
    @PostMapping(value = "/update.do/{id}")
    protected CommonResultVo update(@PathVariable Integer id , @RequestBody ProductVo vo){
        if(null == vo.getProductId()){
            return new CommonResultVo().validateFailed("商品编号为空！");
        }
        return productService.update(id, vo);
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
    @PostMapping(value = "/delete.do")
    protected CommonResultVo delete(Integer... ids){
        CommonResultVo result = productService.deleteList(ids);
        return result;
    }

    /**
     * 修改是否上下架状态
     * @param isPutaway
     * @param ids
     * @return
     */
    @PostMapping(value = "/updateIsPutaway.do")
    protected CommonResultVo updateIsPutaway(String isPutaway, Integer...ids){
        CommonResultVo result = productService.updateIsPutAway(isPutaway, ids);
        return result;
    }
}
