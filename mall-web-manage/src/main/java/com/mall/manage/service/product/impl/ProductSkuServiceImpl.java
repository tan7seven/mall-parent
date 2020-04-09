package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.manage.model.vo.product.sku.SkuAttrParam;
import com.mall.manage.model.vo.product.sku.SkuCreateItemParam;
import com.mall.manage.model.vo.product.sku.SkuCreateParam;
import com.mall.manage.model.vo.product.sku.SkuListVO;
import com.mall.manage.service.product.ProductAttrNameService;
import com.mall.manage.service.product.ProductAttrValueService;
import com.mall.manage.service.product.ProductService;
import com.mall.manage.service.product.ProductSkuService;
import com.mall.manage.service.product.utils.SkuUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service(value = "productSkuService")
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuEntity> implements ProductSkuService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private ProductAttrNameService productAttrNameService;

    @Override
    public Page<ProductSkuDTO> findPage(Integer pageNum, Integer pageSize) {
        ProductSkuDTO queryParam = new ProductSkuDTO();
        Page pageParam = new Page<>(pageNum, pageSize);
        Page<ProductSkuDTO> result = productSkuMapper.getList(pageParam, queryParam);
        return result;
    }

    @Override
    public Boolean createSku(SkuCreateParam param) {
        ProductEntity product = productService.getById(param.getProductId());
        if (Objects.isNull(product)) {
            throw new BusinessException("无效的商品ID");
        }
        List<ProductSkuEntity> insertList = Lists.newArrayList();
        for (SkuCreateItemParam itemParam : param.getParam()) {
            ProductSkuEntity entity = new ProductSkuEntity();
            entity.setProductId(param.getProductId());
            entity.setSalePrice(itemParam.getSalePrice());
            entity.setCostPrice(itemParam.getCostPrice());
            entity.setStock(itemParam.getStock());
            for (SkuAttrParam attrParam : itemParam.getAttrValueList()) {
                Map<String, String> atrJsonMap = Maps.newHashMap();
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public RestResult update(Long id, ProductSkuDTO dto) {
        dto.setCreateTime(new Date());
        dto.setModifyTime(new Date());
        dto.setStock(dto.getStock()==null?0:dto.getStock());
        dto.setSellSum(dto.getSellSum()==null?0:dto.getSellSum());
        dto.setId(id);
        ProductSkuEntity skuEntity = this.getById(id);
        BeanUtils.copyProperties(dto, skuEntity);
        StringBuffer properties = new StringBuffer();
        skuEntity.setAttrJson(properties.toString());
        this.save(skuEntity);
        return RestResult.success();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<SkuListVO> getSkuByProductId(Long productId) {
        List<ProductSkuEntity> skuEntityList = this.list(Wrappers.<ProductSkuEntity>lambdaQuery()
                .eq(ProductSkuEntity::getProductId, productId));
        if (CollectionUtils.isEmpty(skuEntityList)) {
            return Lists.newArrayList();
        }
        List<ProductAttrValueEntity> attrValueList =  productAttrValueService.list(Wrappers.<ProductAttrValueEntity>lambdaQuery()
                .eq(ProductAttrValueEntity::getProductId, productId)
                .eq(ProductAttrValueEntity::getType, ProductAttrNameTypeEnum.SALE.getCode()));
        List<SkuListVO> result = SkuUtil.setSkuListVO(skuEntityList, attrValueList);
        return result;
    }
}
