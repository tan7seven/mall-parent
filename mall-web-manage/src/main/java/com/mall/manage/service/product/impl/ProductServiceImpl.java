package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.ImgTypeEnum;
import com.mall.common.exception.BusinessException;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.entity.product.*;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.manage.manage.product.ProductManage;
import com.mall.manage.model.param.product.product.CreateParam;
import com.mall.manage.model.param.product.product.UpdateIsPutawayParam;
import com.mall.manage.model.param.product.product.UpdateParam;
import com.mall.manage.model.vo.product.product.ProductDetailVO;
import com.mall.manage.service.product.*;
import com.mall.manage.service.product.utils.ProductUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {
    @Autowired
    private ProductManage productManage;
    @Autowired
    private ProductImgService productImgService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private ProductAttrNameService productAttrNameService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailService productDetailService;

    @Override
    public Page<ProductDTO> findPage(Long typeId, String productName, Boolean putaway, Integer pageNum, Integer pageSize) {
        ProductDTO queryParam = new ProductDTO();
        Page pageParam = new Page<>(pageNum, pageSize);
        if (StringUtils.isNotBlank(productName)) {
            queryParam.setProductName(productName);
        }
        if (null != typeId) {
            queryParam.setTypeId(typeId);
        }
        if (null != putaway) {
            queryParam.setPutaway(putaway);
        }
        Page<ProductDTO> result = productMapper.getList(pageParam, queryParam);
        return result;
    }

    @Override
    public Boolean createProduct(CreateParam param) {
        ProductEntity productEntity = ProductUtil.buildCreateProductEntity(param);
        Boolean saveResult = this.save(productEntity);
        if (!saveResult) {
            throw new BusinessException("新增商品信息失败");
        }
        /** 保存商品图片*/
        if (!CollectionUtils.isEmpty(param.getPicList())) {
            productImgService.saveOrUpdateImg(param.getPicList(), productEntity.getId(), ImgTypeEnum.PRODUCT);
        }
        /** 保存商品属性*/
        productManage.saveOrUpdateAttrValue(param.getAttrValueString(), productEntity.getAttrTypeId(), productEntity.getId());

        /** 保持商品详情*/
        productManage.saveOrUpdateDetail(param.getDetail(), productEntity.getId());

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateProduct(UpdateParam param) {
        ProductEntity productEntity = ProductUtil.buildUpdateProductEntity(param);
        Boolean saveResult = this.updateById(productEntity);
        if (!saveResult) {
            throw new BusinessException("修改商品信息失败");
        }
        /** 保存商品图片*/
        if (!CollectionUtils.isEmpty(param.getPicList())) {
            productImgService.saveOrUpdateImg(param.getPicList(), productEntity.getId(), ImgTypeEnum.PRODUCT);
        }

        /** 保持商品详情*/
        productManage.saveOrUpdateDetail(param.getDetail(), productEntity.getId());

        /** 保存商品属性*/
        productManage.saveOrUpdateAttrValue(param.getAttrValueString(), param.getAttrTypeId(), productEntity.getId());

        return Boolean.TRUE;
    }

    @Override
    public ProductDetailVO getProductDetail(Long id) {
        ProductEntity productEntity = this.baseMapper.selectById(id);
        if (Objects.isNull(productEntity)) {
            throw new BusinessException("查无数据");
        }
        ProductDetailVO result = ProductUtil.buildDetailProductVO(productEntity);
        /** 上级类目ID*/
        ProductTypeEntity typeEntity = productTypeService.getById(productEntity.getTypeId());
        result.setProductTypeParentId(typeEntity.getParentId());

        /** 商品明细 */
        ProductDetailEntity detailEntity = productDetailService.getOne(Wrappers.<ProductDetailEntity>lambdaQuery().eq(ProductDetailEntity::getProductId, id));
        result.setDetail(null == detailEntity ?  "" : detailEntity.getDetail());

        /** 商品轮播图*/
        List<ProductImgEntity> imgEntityList = productImgService.list(Wrappers.<ProductImgEntity>lambdaQuery()
                .eq(ProductImgEntity::getForeignId, id)
                .eq(ProductImgEntity::getTypeCode, ImgTypeEnum.PRODUCT.getCode()));
        result.setPicList(imgEntityList.stream().map(s -> CommonConstant.IMG_PRE + s.getImgUrl()).collect(Collectors.toList()));

        /** 设置属性名*/
        List<ProductAttrNameEntity> attrNameList = productAttrNameService.list(Wrappers.<ProductAttrNameEntity>lambdaQuery()
                .eq(ProductAttrNameEntity::getTypeId, productEntity.getAttrTypeId()));
        ProductUtil.setDetailAttrNameList(attrNameList, result);

        /** 设置属性值*/
        List<ProductAttrValueEntity> attrValueList = productAttrValueService.list(Wrappers.<ProductAttrValueEntity>lambdaQuery()
                .eq(ProductAttrValueEntity::getProductId, id));
        ProductUtil.setDetailAttrValueList(attrValueList, attrNameList, result);
        return result;
    }

    @Override
    public Boolean updateIsPutAway(UpdateIsPutawayParam param) {
        List<ProductEntity> updateList = Lists.newArrayList();
        for (Long id : param.getIds()) {
            ProductEntity entity = new ProductEntity();
            entity.setId(id);
            entity.setPutaway(param.getPutaway());
            updateList.add(entity);
        }
        Boolean result = this.updateBatchById(updateList);
        return result;
    }
    @Override
    public Boolean deleteList(List<Integer> ids) {
        Boolean result = this.removeByIds(ids);
        return result;
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        ProductDTO dto = new ProductDTO();
        if (StringUtils.isNotBlank(name)) {
            dto.setProductName(name);
        }
        return null;
    }
}
