package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.constant.CommonConstant;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.dto.product.ProductPropertyDTO;
import com.mall.dao.entity.product.*;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.dao.mapper.product.ProductPropertyNameMapper;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.manage.param.product.product.GetPageParam;
import com.mall.manage.param.product.product.UpdateIsPutawayParam;
import com.mall.manage.service.product.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {
    @Value("${pic.path}")
    private String picPath;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductTypeService productTypeRepository;
    @Autowired
    private ProductPropertyNameService productPropertyNameService;
    @Autowired
    private ProductPropertyValueService productPropertyValueService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductPropertyNameMapper productPropertyMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductImgService productImgService;

    @Override
    public ProductDTO findById(Integer id) {
        ProductEntity entity = this.getById(id);
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        //获取商品销售属性值
        List<ProductPropertyValueEntity> propertyValues = productPropertyValueService.findByProductId(id);
        List<Integer> properties = propertyValues.stream()
                .filter(productPropertyValueEntity -> ProductPropertyValueEntity.IS_SALE.equals(productPropertyValueEntity.getIsSale()))
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
        ProductEntity result = this.getById(id);
        ProductTypeEntity typeEntity = productTypeRepository.findById(result.getTypeId()).get();
        BeanUtils.copyProperties(result, dto);
        dto.setTypeName(typeEntity.getTypeName());
        dto.setProductTypeParentId(typeEntity.getParentId());
        dto.setProductTypeId(result.getTypeId());
        //获取商品销售属性值
        ProductDTO isSaleParam = new ProductDTO();
        isSaleParam.setProductId(dto.getProductId());
        isSaleParam.setIsSale(ProductPropertyNameEntity.IS_SALE);
        List<ProductPropertyDTO> propertyIsSaleList = productMapper.findPropertyIsSale(isSaleParam);
        dto.setProductPropertyIsSaleChecked(propertyIsSaleList.stream().map(ProductPropertyDTO::getIsSalePropertyString).toArray(String[]::new));
        //获取商品销售属性值
        ProductDTO notSaleParam = new ProductDTO();
        notSaleParam.setProductId(dto.getProductId());
        notSaleParam.setIsSale(ProductPropertyNameEntity.NOT_SALE);
        List<String> propertyNotList = productMapper.findPropertyNotSale(notSaleParam);
        dto.setProductPropertyNotSaleChecked(propertyNotList.stream().toArray(String[]::new));
        //获取商品明细
        List<ProductDetailEntity> detailEntityList = productDetailService.list(Wrappers.<ProductDetailEntity>lambdaQuery()
                .eq(ProductDetailEntity::getProductId, dto.getProductId()));
        if (null != detailEntityList && !detailEntityList.isEmpty()) {
            dto.setDetail(detailEntityList.get(0).getDetail());
        }
        //商品图片
        List<ProductImgEntity> imgEntityList = productImgService.list(Wrappers.<ProductImgEntity>lambdaQuery()
                .eq(ProductImgEntity::getForeignId, String.valueOf(id))
                .eq(ProductImgEntity::getTypeCode, ProductImgEntity.TYPE_CODE_PRODUCT));
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
            productDetailService.remove(Wrappers.<ProductDetailEntity>lambdaQuery().eq(ProductDetailEntity::getProductId, id));
            //删除商品属性值
            productPropertyMapper.deleteByProductId(id);
            //删除商品信息-逻辑删除
            productMapper.updateIsDelete(id);
        }
        return RestResult.success();
    }

    @Override
    public Page<ProductDTO> findPage(GetPageParam param) {
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(param, dto);
        List<ProductDTO> productVoList = productMapper.getList(dto);
        return new Page<>();
    }

    @Override
    public RestResult create(ProductDTO dto) {
        //添加商品信息
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setIsPutaway(ProductEntity.PUT_AWAY);
        entity.setIsUsable(CommonConstant.IS_USABLE);
        entity.setIsDelete(CommonConstant.NOT_DELETE);
        entity.setCreateTime(new Date());
        entity.setTypeId(dto.getProductTypeId());
        entity.setHits(0);
        if (null != dto.getPicUrlArray() && dto.getPicUrlArray().length > 0) {
            //商品首页图片默认是第一张，其他的则保持到图片信息表里面
            entity.setPicUrl(dto.getPicUrlArray()[0]);

        }
        this.save(entity);
        if (null != dto.getPicUrlArray() && dto.getPicUrlArray().length > 0) {
            //商品首页图片默认是第一张，其他的则保持到图片信息表里面
            java.lang.String[] picUrls = dto.getPicUrlArray();
            for (String picUrl : picUrls) {
                ProductImgEntity imgEntity = new ProductImgEntity();
                imgEntity.setCreateTime(new Date());
                imgEntity.setForeignId(String.valueOf(entity.getProductId()));
                imgEntity.setImgUrl(picUrl);
                imgEntity.setTypeCode(ProductImgEntity.TYPE_CODE_PRODUCT);
                productImgService.save(imgEntity);
            }
        }
        //添加商品明细
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        productDetailEntity.setDetail(dto.getDetail());
        productDetailEntity.setProductId(entity.getProductId());
        productDetailService.save(productDetailEntity);
        //添加销售属性值
        String[] propertyIsSales = dto.getProductPropertyIsSaleChecked();

        for (String propertyIsSale : propertyIsSales) {

            String[] proerties = propertyIsSale.split(":");
            if (proerties.length < 2) {
                continue;
            }
            List<ProductPropertyNameEntity> propertyNameEntities =
                    productPropertyNameService.list(Wrappers.<ProductPropertyNameEntity>lambdaQuery()
                            .eq(ProductPropertyNameEntity::getTypeId, dto.getProductTypeId())
                            .eq(ProductPropertyNameEntity::getName, proerties[0])
                            .eq(ProductPropertyNameEntity::getIsDelete, CommonConstant.NOT_DELETE));
            if (null == propertyNameEntities && propertyNameEntities.size() != 1) {
                continue;
            }
            String propertyValue = proerties[proerties.length - 1];
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setPropertyNameId(propertyNameEntities.get(0).getPropertyNameId());
            propertyValueEntity.setValue(propertyValue);
            propertyValueEntity.setProductId(entity.getProductId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.IS_SALE);
            productPropertyValueService.save(propertyValueEntity);
        }
        //添加非销售属性值
        String[] propertyNotSales = dto.getProductPropertyNotSaleChecked();
        List<ProductPropertyNameEntity> propertyNotSales2 =
                productPropertyNameService.list(Wrappers.<ProductPropertyNameEntity>lambdaQuery()
                        .eq(ProductPropertyNameEntity::getTypeId, dto.getProductTypeId())
                        .eq(ProductPropertyNameEntity::getIsSale, ProductPropertyNameEntity.NOT_SALE)
                        .eq(ProductPropertyNameEntity::getIsDelete, CommonConstant.NOT_DELETE));
        for (int i = 0; i < propertyNotSales.length; i++) {
            if (null == propertyNotSales2 || propertyNotSales2.size() < i + 1) {
                break;
            }
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setValue(propertyNotSales[i]);
            propertyValueEntity.setProductId(entity.getProductId());
            propertyValueEntity.setPropertyNameId(propertyNotSales2.get(i).getPropertyNameId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.NOT_SALE);
            productPropertyValueService.save(propertyValueEntity);
        }
        return RestResult.success();
    }

    @Override
    public RestResult update(Integer productId, ProductDTO dto) {
        //修改商品信息
        ProductEntity entity = this.getById(productId);
        BeanUtils.copyProperties(dto, entity);
        productImgService.remove(Wrappers.<ProductImgEntity>lambdaQuery()
                .eq(ProductImgEntity::getForeignId, String.valueOf(productId))
                .eq(ProductImgEntity::getTypeCode, ProductImgEntity.TYPE_CODE_PRODUCT));
        //删除图片
        if (null != dto.getPicUrlArray() && dto.getPicUrlArray().length > 0) {
            //商品首页图片默认是第一张，其他的则保持到图片信息表里面
            java.lang.String[] picUrls = dto.getPicUrlArray();
            entity.setPicUrl(picUrls[0]);
            for (String picUrl : picUrls) {
                ProductImgEntity imgEntity = new ProductImgEntity();
                imgEntity.setCreateTime(new Date());
                imgEntity.setForeignId(String.valueOf(productId));
                imgEntity.setImgUrl(picUrl);
                imgEntity.setTypeCode(ProductImgEntity.TYPE_CODE_PRODUCT);
                productImgService.save(imgEntity);
            }
        }


        Boolean resultProduct = this.save(entity);
        //修改商品明细
        productDetailService.remove(Wrappers.<ProductDetailEntity>lambdaQuery()
                .eq(ProductDetailEntity::getProductId, productId));
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        productDetailEntity.setDetail(dto.getDetail());
        productDetailEntity.setProductId(productId);
        productDetailService.save(productDetailEntity);
        //修改销售属性值
        String[] propertyIsSales = dto.getProductPropertyIsSaleChecked();
        //获取商品销售属性值
        ProductDTO isSaleParam = new ProductDTO();
        isSaleParam.setProductId(dto.getProductId());
        isSaleParam.setIsSale(ProductPropertyNameEntity.IS_SALE);
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
            List<ProductPropertyNameEntity> propertyNameEntities = productPropertyNameService.list(Wrappers.<ProductPropertyNameEntity>lambdaQuery()
                            .eq(ProductPropertyNameEntity::getTypeId, dto.getProductTypeId())
                            .eq(ProductPropertyNameEntity::getName, proerties[0])
                            .eq(ProductPropertyNameEntity::getIsSale, CommonConstant.NOT_DELETE));
            if (null == propertyNameEntities && propertyNameEntities.size() != 1) {
                continue;
            }
            String propertyValue = proerties[proerties.length - 1];
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setPropertyNameId(propertyNameEntities.get(0).getPropertyNameId());
            propertyValueEntity.setValue(propertyValue);
            propertyValueEntity.setProductId(entity.getProductId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.IS_SALE);
            productPropertyValueService.save(propertyValueEntity);
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
            productPropertyValueService.deleteById(Integer.valueOf(proerties[proerties.length - 1]));

        }
        //修改非销售属性值->先删除之前的值再添加
        String[] propertyNotSales = dto.getProductPropertyNotSaleChecked();
        productPropertyValueService.remove(Wrappers.<ProductPropertyValueEntity>lambdaQuery()
                .eq(ProductPropertyValueEntity::getProductId, productId)
                .eq(ProductPropertyValueEntity::getIsSale, ProductPropertyValueEntity.NOT_SALE));
        List<ProductPropertyNameEntity> propertyNotSales2 =
                productPropertyNameService.list(Wrappers.<ProductPropertyNameEntity>lambdaQuery()
                        .eq(ProductPropertyNameEntity::getTypeId, dto.getProductTypeId())
                        .eq(ProductPropertyNameEntity::getIsSale, ProductPropertyNameEntity.NOT_SALE)
                        .eq(ProductPropertyNameEntity::getIsDelete, CommonConstant.NOT_DELETE));
        for (int i = 0; i < propertyNotSales.length; i++) {
            if (null == propertyNotSales2 || propertyNotSales2.size() < i + 1) {
                break;
            }
            ProductPropertyValueEntity propertyValueEntity = new ProductPropertyValueEntity();
            propertyValueEntity.setValue(propertyNotSales[i]);
            propertyValueEntity.setProductId(entity.getProductId());
            propertyValueEntity.setPropertyNameId(propertyNotSales2.get(i).getPropertyNameId());
            propertyValueEntity.setIsSale(ProductPropertyValueEntity.NOT_SALE);
            productPropertyValueService.save(propertyValueEntity);
        }

        return RestResult.success();
    }

    @Override
    public RestResult updateIsPutAway(UpdateIsPutawayParam param) {
        for (Integer id : param.getIds()) {
            ProductEntity entity = this.getById(id);
            entity.setIsPutaway(param.getIsPutaway());
            this.save(entity);
        }
        return RestResult.success();
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        ProductDTO dto = new ProductDTO();
        if (StringUtils.isNotBlank(name)) {
            dto.setProductName(name);
        }
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
