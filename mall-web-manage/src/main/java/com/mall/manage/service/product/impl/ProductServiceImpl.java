package com.mall.manage.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.constant.CommonConstant;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.dto.product.ProductPropertyDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.dao.mapper.product.ProductPropertyMapper;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.dao.repository.product.*;
import com.mall.manage.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {
    @Value("${pic.path}")
    private String picPath;
    @Autowired
    private ProductSkuRepository productSkuRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;
    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductPropertyMapper productPropertyMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductImgRepository productImgRepository;

    @Override
    public com.mall.dao.entity.product.ProductEntity add(com.mall.dao.entity.product.ProductEntity entity) {
        entity.setCreateTime(new Date());
        return productRepository.save(entity);
    }

    @Override
    public ProductDTO findById(Integer id) {
        com.mall.dao.entity.product.ProductEntity entity = productRepository.findById(id).get();
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        //获取商品销售属性值
        List<com.mall.dao.entity.product.ProductPropertyValueEntity> propertyValues = productPropertyValueRepository.findByProductId(id);
        List<Integer> properties = propertyValues.stream()
                .filter(productPropertyValueEntity -> com.mall.dao.entity.product.ProductPropertyValueEntity.IS_SALE.equals(productPropertyValueEntity.getIsSale()))
                .map(productPropertyValueEntity -> productPropertyValueEntity.getPropertyNameId())
                .distinct().collect(Collectors.toList());
        if (null != properties && !properties.isEmpty() && properties.size() >= 1) {
            dto.setPropertyValueAOptions(productPropertyMapper.findByPropertyNameIdAndProductId(properties.get(0), entity.getProductId()));
        }
        if (null != properties && !properties.isEmpty() && properties.size() >= 2) {
            dto.setPropertyValueBOptions(productPropertyMapper.findByPropertyNameIdAndProductId(properties.get(1), entity.getProductId()));
        }
        if (null != properties && !properties.isEmpty() && properties.size() >= 3) {
            dto.setPropertyValueCOptions(productPropertyMapper.findByPropertyNameIdAndProductId(properties.get(2), entity.getProductId()));
        }
        com.mall.dao.entity.product.ProductEntity result = productRepository.findById(id).get();
        com.mall.dao.entity.product.ProductTypeEntity typeEntity = productTypeRepository.findById(result.getTypeId()).get();
        BeanUtils.copyProperties(result, dto);
        dto.setTypeName(typeEntity.getTypeName());
        dto.setProductTypeParentId(typeEntity.getParentId());
        dto.setProductTypeId(result.getTypeId());
        //获取商品销售属性值
        ProductDTO isSaleParam = new ProductDTO();
        isSaleParam.setProductId(dto.getProductId());
        isSaleParam.setIsSale(com.mall.dao.entity.product.ProductPropertyNameEntity.IS_SALE);
        List<ProductPropertyDTO> propertyIsSaleList = productMapper.findPropertyIsSale(isSaleParam);
        dto.setProductPropertyIsSaleChecked(propertyIsSaleList.stream().map(ProductPropertyDTO::getIsSalePropertyString).toArray(String[]::new));
        //获取商品销售属性值
        ProductDTO notSaleParam = new ProductDTO();
        notSaleParam.setProductId(dto.getProductId());
        notSaleParam.setIsSale(com.mall.dao.entity.product.ProductPropertyNameEntity.NOT_SALE);
        List<String> propertyNotList = productMapper.findPropertyNotSale(notSaleParam);
        dto.setProductPropertyNotSaleChecked(propertyNotList.stream().toArray(String[]::new));
        //获取商品明细
        List<com.mall.dao.entity.product.ProductDetailEntity> detailEntityList = productDetailRepository.findByProductId(dto.getProductId());
        if (null != detailEntityList && !detailEntityList.isEmpty()) {
            dto.setDetail(detailEntityList.get(0).getDetail());
        }
        //商品图片
        List<com.mall.dao.entity.product.ProductImgEntity> imgEntityList = productImgRepository.findByForeignIdAndAndTypeCode(String.valueOf(id), com.mall.dao.entity.product.ProductImgEntity.TYPE_CODE_PRODUCT);
        dto.setPicUrlArray(imgEntityList.stream().map(e -> e.getImgUrl()).toArray(String[]::new));
        log.info("商品信息：{}", dto);
        return dto;
    }

    @Override
    public RestResult deleteList(List<Integer>  ids) {
        for (Integer id : ids) {
            //删除商品库存信息-逻辑删除
            productSkuMapper.updateIsDeleteByProductId(id);
            //删除商品明细
            productDetailRepository.deleteByProductId(id);
            //删除商品属性值
            productPropertyMapper.deleteByProductId(id);
            //删除商品信息-逻辑删除
            productMapper.updateIsDelete(id);
        }
        return RestResult.success();
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<com.mall.dao.entity.product.ProductEntity> findList(com.mall.dao.entity.product.ProductEntity entity) {
        Example<com.mall.dao.entity.product.ProductEntity> example = Example.of(entity);
        List<com.mall.dao.entity.product.ProductEntity> result = productRepository.findAll(example);
        return result;
    }

    @Override
    public Page<com.mall.dao.entity.product.ProductEntity> findPage(com.mall.dao.entity.product.ProductEntity entity, Pageable page) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("productName", ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                ;
        Example<com.mall.dao.entity.product.ProductEntity> example = Example.of(entity, matcher);
        Page<com.mall.dao.entity.product.ProductEntity> result = productRepository.findAll(example, page);
        return result;
    }

    @Override
    public PageInfo<ProductDTO> findPage(ProductDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<ProductDTO> productVoList = productMapper.getList(dto);
        PageInfo<ProductDTO> page = new PageInfo<>(productVoList);
        return page;
    }

    @Override
    public RestResult create(ProductDTO dto) {
        //添加商品信息
        com.mall.dao.entity.product.ProductEntity entity = new com.mall.dao.entity.product.ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setIsPutaway(com.mall.dao.entity.product.ProductEntity.PUT_AWAY);
        entity.setIsUsable(CommonConstant.IS_USABLE);
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        entity.setCreateTime(new Date());
        entity.setTypeId(dto.getProductTypeId());
        entity.setHits(0);
        if (null != dto.getPicUrlArray() && dto.getPicUrlArray().length > 0) {
            //商品首页图片默认是第一张，其他的则保持到图片信息表里面
            entity.setPicUrl(dto.getPicUrlArray()[0]);

        }
        com.mall.dao.entity.product.ProductEntity resultProduct = productRepository.save(entity);
        if (null != dto.getPicUrlArray() && dto.getPicUrlArray().length > 0) {
            //商品首页图片默认是第一张，其他的则保持到图片信息表里面
            java.lang.String[] picUrls = dto.getPicUrlArray();
            for (String picUrl : picUrls) {
                com.mall.dao.entity.product.ProductImgEntity imgEntity = new com.mall.dao.entity.product.ProductImgEntity();
                imgEntity.setCreateTime(new Date());
                imgEntity.setForeignId(String.valueOf(resultProduct.getProductId()));
                imgEntity.setImgUrl(picUrl);
                imgEntity.setTypeCode(com.mall.dao.entity.product.ProductImgEntity.TYPE_CODE_PRODUCT);
                productImgRepository.save(imgEntity);
            }
        }
        //添加商品明细
        com.mall.dao.entity.product.ProductDetailEntity productDetailEntity = new com.mall.dao.entity.product.ProductDetailEntity();
        productDetailEntity.setDetail(dto.getDetail());
        productDetailEntity.setProductId(resultProduct.getProductId());
        productDetailRepository.save(productDetailEntity);
        //添加销售属性值
        String[] propertyIsSales = dto.getProductPropertyIsSaleChecked();

        for (String propertyIsSale : propertyIsSales) {

            String[] proerties = propertyIsSale.split(":");
            if (proerties.length < 2) {
                continue;
            }
            List<com.mall.dao.entity.product.ProductPropertyNameEntity> propertyNameEntities =
                    productPropertyNameRepository.findByTypeIdAndNameAndIsDelete(dto.getProductTypeId(), proerties[0], CommonConstant.NOT_DELETE);
            if (null == propertyNameEntities && propertyNameEntities.size() != 1) {
                continue;
            }
            String propertyValue = proerties[proerties.length - 1];
            com.mall.dao.entity.product.ProductPropertyValueEntity propertyValueEntity = new com.mall.dao.entity.product.ProductPropertyValueEntity();
            propertyValueEntity.setPropertyNameId(propertyNameEntities.get(0).getPropertyNameId());
            propertyValueEntity.setValue(propertyValue);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setIsSale(com.mall.dao.entity.product.ProductPropertyValueEntity.IS_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }
        //添加非销售属性值
        String[] propertyNotSales = dto.getProductPropertyNotSaleChecked();
        List<com.mall.dao.entity.product.ProductPropertyNameEntity> propertyNotSales2 =
                productPropertyNameRepository.findByTypeIdAndIsSaleAndIsDelete(dto.getProductTypeId(), com.mall.dao.entity.product.ProductPropertyNameEntity.NOT_SALE, CommonConstant.NOT_DELETE);
        for (int i = 0; i < propertyNotSales.length; i++) {
            if (null == propertyNotSales2 || propertyNotSales2.size() < i + 1) {
                break;
            }
            com.mall.dao.entity.product.ProductPropertyValueEntity propertyValueEntity = new com.mall.dao.entity.product.ProductPropertyValueEntity();
            propertyValueEntity.setValue(propertyNotSales[i]);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setPropertyNameId(propertyNotSales2.get(i).getPropertyNameId());
            propertyValueEntity.setIsSale(com.mall.dao.entity.product.ProductPropertyValueEntity.NOT_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }
        return RestResult.success();
    }

    @Override
    public RestResult update(Integer productId, ProductDTO dto) {
        //修改商品信息
        com.mall.dao.entity.product.ProductEntity entity = productRepository.findById(productId).get();
        BeanUtils.copyProperties(dto, entity);
        productImgRepository.deleteByForeignIdAndTypeCode(String.valueOf(productId), com.mall.dao.entity.product.ProductImgEntity.TYPE_CODE_PRODUCT);
        //删除图片
        if (null != dto.getPicUrlArray() && dto.getPicUrlArray().length > 0) {
            //商品首页图片默认是第一张，其他的则保持到图片信息表里面
            java.lang.String[] picUrls = dto.getPicUrlArray();
            entity.setPicUrl(picUrls[0]);
            for (String picUrl : picUrls) {
                com.mall.dao.entity.product.ProductImgEntity imgEntity = new com.mall.dao.entity.product.ProductImgEntity();
                imgEntity.setCreateTime(new Date());
                imgEntity.setForeignId(String.valueOf(productId));
                imgEntity.setImgUrl(picUrl);
                imgEntity.setTypeCode(com.mall.dao.entity.product.ProductImgEntity.TYPE_CODE_PRODUCT);
                productImgRepository.save(imgEntity);
            }
        }


        com.mall.dao.entity.product.ProductEntity resultProduct = productRepository.save(entity);
        //修改商品明细
        productDetailRepository.deleteByProductId(productId);
        com.mall.dao.entity.product.ProductDetailEntity productDetailEntity = new com.mall.dao.entity.product.ProductDetailEntity();
        productDetailEntity.setDetail(dto.getDetail());
        productDetailEntity.setProductId(productId);
        productDetailRepository.save(productDetailEntity);
        //修改销售属性值
        String[] propertyIsSales = dto.getProductPropertyIsSaleChecked();
        //获取商品销售属性值
        ProductDTO isSaleParam = new ProductDTO();
        isSaleParam.setProductId(dto.getProductId());
        isSaleParam.setIsSale(com.mall.dao.entity.product.ProductPropertyNameEntity.IS_SALE);
        List<ProductPropertyDTO> propertyIsSaleList = productMapper.findPropertyIsSale(isSaleParam);
        //判断之前是否存在，存在则不操作，不存在则新增
        for (String propertyIsSale : propertyIsSales) {
            List<String> isExist = propertyIsSaleList.stream().map(ProductPropertyDTO::getIsSalePropertyString).filter(s -> s.equals(propertyIsSale)).collect(Collectors.toList());
            if (null != isExist && !isExist.isEmpty()) {
                continue;
            }
            String[] proerties = propertyIsSale.split(":");
            if (proerties.length < 2) {
                continue;
            }
            List<com.mall.dao.entity.product.ProductPropertyNameEntity> propertyNameEntities =
                    productPropertyNameRepository.findByTypeIdAndNameAndIsDelete(dto.getProductTypeId(), proerties[0], CommonConstant.NOT_DELETE);
            if (null == propertyNameEntities && propertyNameEntities.size() != 1) {
                continue;
            }
            String propertyValue = proerties[proerties.length - 1];
            com.mall.dao.entity.product.ProductPropertyValueEntity propertyValueEntity = new com.mall.dao.entity.product.ProductPropertyValueEntity();
            propertyValueEntity.setPropertyNameId(propertyNameEntities.get(0).getPropertyNameId());
            propertyValueEntity.setValue(propertyValue);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setIsSale(com.mall.dao.entity.product.ProductPropertyValueEntity.IS_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }
        //判断之前的值是否被删除，删除需要删除对应的SKU信息
        for (ProductPropertyDTO propertyIsSale : propertyIsSaleList) {
            List<String> isExist = Arrays.stream(propertyIsSales)
                    .filter(s -> s.equals(propertyIsSale.getIsSalePropertyString()))
                    .collect(Collectors.toList());
            if (null != isExist && !isExist.isEmpty()) {
                continue;
            }
            //删除SKU
            productSkuMapper.deleteByIsSaleProperty(propertyIsSale.getIsSalePropertyId());
            String[] proerties = propertyIsSale.getIsSalePropertyId().split(":");
            if (proerties.length < 2) {
                continue;
            }
            //删除属性值
            productPropertyValueRepository.deleteById(Integer.valueOf(proerties[proerties.length - 1]));

        }
        //修改非销售属性值->先删除之前的值再添加
        String[] propertyNotSales = dto.getProductPropertyNotSaleChecked();
        productPropertyValueRepository.deleteByProductIdAndIsSale(productId, com.mall.dao.entity.product.ProductPropertyValueEntity.NOT_SALE);
        List<com.mall.dao.entity.product.ProductPropertyNameEntity> propertyNotSales2 =
                productPropertyNameRepository.findByTypeIdAndIsSaleAndIsDelete(dto.getProductTypeId(), ProductPropertyNameEntity.NOT_SALE, CommonConstant.NOT_DELETE);
        for (int i = 0; i < propertyNotSales.length; i++) {
            if (null == propertyNotSales2 || propertyNotSales2.size() < i + 1) {
                break;
            }
            com.mall.dao.entity.product.ProductPropertyValueEntity propertyValueEntity = new com.mall.dao.entity.product.ProductPropertyValueEntity();
            propertyValueEntity.setValue(propertyNotSales[i]);
            propertyValueEntity.setProductId(resultProduct.getProductId());
            propertyValueEntity.setPropertyNameId(propertyNotSales2.get(i).getPropertyNameId());
            propertyValueEntity.setIsSale(com.mall.dao.entity.product.ProductPropertyValueEntity.NOT_SALE);
            productPropertyValueRepository.save(propertyValueEntity);
        }

        return RestResult.success();
    }

    @Override
    public RestResult updateIsPutAway(String isPutaway, List<Integer>  ids) {
        for (Integer id : ids) {
            com.mall.dao.entity.product.ProductEntity entity = productRepository.findById(id).get();
            entity.setIsPutaway(isPutaway);
            productRepository.save(entity);
        }
        return RestResult.success();
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        ProductDTO dto = new ProductDTO();
        if (StringUtils.isNotBlank(name)) {
            dto.setProductName(name);
        }
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        return productMapper.getList(dto);
    }

    /**
     * 删除图片
     *
     * @param picUrl
     * @return
     */
    private Object deletePic(String picUrl) {
        int lastIndexOf = picUrl.lastIndexOf("/");
        String sb = picUrl.substring(lastIndexOf + 1, picUrl.length());
        sb = picPath + sb;
        File file = new File(sb);
        if (file.exists()) {
            if (file.delete()) {
                return RestResult.success();
            } else {
                return RestResult.failed();
            }
        } else {
            return RestResult.failed();
        }
    }
}
