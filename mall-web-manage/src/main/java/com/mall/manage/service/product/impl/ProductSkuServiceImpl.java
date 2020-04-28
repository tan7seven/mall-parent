package com.mall.manage.service.product.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.exception.BusinessException;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductAttrValueEntity;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.manage.model.param.product.sku.SkuCreateItemParam;
import com.mall.manage.model.param.product.sku.SkuCreateParam;
import com.mall.manage.model.vo.product.sku.SkuListVO;
import com.mall.manage.service.product.ProductAttrValueService;
import com.mall.manage.service.product.ProductService;
import com.mall.manage.service.product.ProductSkuService;
import com.mall.manage.service.product.utils.SkuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service(value = "productSkuService")
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSkuEntity> implements ProductSkuService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Override
    public Page<ProductSkuDTO> findPage(String productName, Integer pageNum, Integer pageSize) {
        ProductSkuDTO queryParam = new ProductSkuDTO();
        Page pageParam = new Page<>(pageNum, pageSize);
        queryParam.setProductName(productName);
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
        List<ProductSkuEntity> updateList = Lists.newArrayList();

        for (SkuCreateItemParam itemParam : param.getParam()) {
            ProductSkuEntity entity = new ProductSkuEntity();
            entity.setProductId(param.getProductId());
            entity.setSalePrice(itemParam.getSalePrice());
            entity.setCostPrice(itemParam.getCostPrice());
            entity.setStock(itemParam.getStock());
            entity.setAttrJson(JSONObject.toJSONString(itemParam.getAttrValueList()));
            if (null != itemParam.getId()) {
                entity.setId(itemParam.getId());
                updateList.add(entity);
            }else{
                insertList.add(entity);
            }
        }
        BigDecimal minPrice = param.getParam().stream().map(s -> s.getSalePrice()).min(BigDecimal::compareTo).get();
        product.setMinPrice(minPrice);
        productService.updateById(product);
        /** 删除SKU*/
        List<ProductSkuEntity> oldSku = this.list(Wrappers.<ProductSkuEntity>lambdaQuery().eq(ProductSkuEntity::getProductId, param.getProductId()));
        if (!CollectionUtils.isEmpty(oldSku)) {
            List<Long> oldSkuIdList = oldSku.stream().map(s -> s.getId()).collect(Collectors.toList());
            List<Long> removeList = Lists.newArrayList();
            List<Long> updateIdList = updateList.stream().map(s -> s.getId()).collect(Collectors.toList());
            for (Long aLong : oldSkuIdList) {
                if (!updateIdList.contains(aLong)) {
                    removeList.add(aLong);
                }
            }
            if (!CollectionUtils.isEmpty(removeList)) {
                this.removeByIds(removeList);
            }
        }
        if (!CollectionUtils.isEmpty(insertList)) {
            this.saveBatch(insertList);
        }
        if (!CollectionUtils.isEmpty(updateList)) {
            this.updateBatchById(updateList);
        }
        return Boolean.TRUE;
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
