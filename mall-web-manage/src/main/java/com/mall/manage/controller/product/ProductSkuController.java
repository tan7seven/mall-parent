package com.mall.manage.controller.product;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.controller.product.util.SkuUtil;
import com.mall.manage.model.param.product.sku.SkuCreateParam;
import com.mall.manage.model.vo.product.attr.AttrValueVO;
import com.mall.manage.model.vo.product.sku.SkuListVO;
import com.mall.manage.model.vo.product.sku.SkuPageVO;
import com.mall.manage.service.product.ProductSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(value = "商品SKU", tags = "商品SKU")
@Slf4j
@RestController
@RequestMapping(value = "/product-sku")
public class ProductSkuController extends GenericController {

    @Resource(name = "productSkuService")
    private ProductSkuService productSkuService;

    @ApiOperation("分页查询")
    @GetMapping(value = "/page/get")
    protected RestResult getPage(@ApiParam(value = "页码") @RequestParam(required = false) String productName,
                                 @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                 @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page<ProductSkuDTO> dtoPage = productSkuService.findPage(productName, pageNum, pageSize);
        RestPage<SkuPageVO> result = new RestPage(dtoPage.getCurrent(), dtoPage.getSize(), dtoPage.getTotal());
        List<SkuPageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtoPage.getRecords())) {
            for (ProductSkuDTO record : dtoPage.getRecords()) {
                SkuPageVO vo = new SkuPageVO();
                BeanUtils.copyProperties(record, vo);
                List<AttrValueVO> valueOldList = JSONObject.parseArray(vo.getAttrJson(), AttrValueVO.class);
                StringBuffer attrValue = new StringBuffer();
                for (AttrValueVO attrValueVO : valueOldList) {
                    attrValue.append("【"+ attrValueVO.getSkuName() + ":" + attrValueVO.getSkuValue() + "】");
                }
                vo.setAttrValue(attrValue.toString());
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
        return RestResult.success(result);
    }

    @ApiOperation("获取商品的SKU列表")
    @GetMapping(value = "/product-id/get")
    protected RestResult<List<SkuListVO>> getSkuByProductId(@ApiParam(value = "类目ID")@RequestParam Long productId){
        List<SkuListVO> result = productSkuService.getSkuByProductId(productId);
        return RestResult.success(result);
    }

    @ApiOperation("新增")
    @PreAuthorize(" hasAuthority('PMS:PRODUCTSKU:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "/create")
    protected RestResult create (@Validated @RequestBody SkuCreateParam param){
        SkuUtil.validatedCreateParam(param);
        Boolean result = productSkuService.createSku(param);
        return RestResult.success(result);
    }
}
