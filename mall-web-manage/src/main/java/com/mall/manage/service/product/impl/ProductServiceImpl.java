package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.enums.ProductAttrNameTypeEnum;
import com.mall.common.vo.RestPage;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductAttrNameDTO;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.dto.product.ProductPropertyDTO;
import com.mall.dao.entity.product.*;
import com.mall.dao.mapper.product.ProductAttrNameMapper;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.dao.mapper.product.ProductSkuMapper;
import com.mall.manage.model.param.product.product.ProductGetPageParam;
import com.mall.manage.model.param.product.product.UpdateIsPutawayParam;
import com.mall.manage.model.vo.product.attr.AttrPageVO;
import com.mall.manage.model.vo.product.product.ProductPageVO;
import com.mall.manage.service.product.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {
    @Value("${pic.path}")
    private String picPath;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductAttValueService productPropertyValueService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductAttrNameMapper productPropertyMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductImgService productImgService;

    @Override
    public ProductDTO queryById(Long id) {
        ProductEntity entity = this.getById(id);
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        //获取商品销售属性值
        List<ProductAttrValueEntity> propertyValues = productPropertyValueService.findByProductId(id);
        List<Long> properties = propertyValues.stream()
                .filter(productPropertyValueEntity -> Boolean.TRUE.equals(productPropertyValueEntity.getType()))
                .map(productPropertyValueEntity -> productPropertyValueEntity.getNameId())
                .distinct().collect(Collectors.toList());

        ProductEntity result = this.getById(id);
        ProductTypeEntity typeEntity = productTypeService.getById(result.getTypeId());
        BeanUtils.copyProperties(result, dto);
        dto.setTypeName(typeEntity.getTypeName());
        //获取商品销售属性值
        ProductDTO isSaleParam = new ProductDTO();
        List<ProductPropertyDTO> propertyIsSaleList = productMapper.findPropertyIsSale(isSaleParam);
        //获取商品销售属性值
        ProductDTO notSaleParam = new ProductDTO();
        List<String> propertyNotList = productMapper.findPropertyNotSale(notSaleParam);

        //商品图片
        List<ProductImgEntity> imgEntityList = productImgService.list(Wrappers.<ProductImgEntity>lambdaQuery()
                .eq(ProductImgEntity::getForeignId, String.valueOf(id))
                .eq(ProductImgEntity::getTypeCode, ProductImgEntity.TYPE_CODE_PRODUCT));
        log.info("商品信息：{}", dto);
        return dto;
    }

    @Override
    public RestResult deleteList(List<Integer>  ids) {
        for (Integer id : ids) {
            //删除商品库存信息-逻辑删除
            productSkuMapper.updateIsDeleteByProductId(id);
            //删除商品明细
            productDetailService.remove(Wrappers.<ProductDetailEntity>lambdaQuery().eq(ProductDetailEntity::getId, id));
            //删除商品属性值
            productPropertyMapper.deleteByProductId(id);
            //删除商品信息-逻辑删除
            productMapper.updateIsDelete(id);
        }
        return RestResult.success();
    }

    @Override
    public RestPage<ProductPageVO> findPage(Long typeId, String productName, Boolean putaway, Integer pageNum, Integer pageSize) {
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
        Page<ProductDTO> dtoPage = productMapper.getList(pageParam, queryParam);
        RestPage<ProductPageVO> result = new RestPage<>(dtoPage.getCurrent(), dtoPage.getSize(), dtoPage.getTotal());
        List<ProductPageVO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dtoPage.getRecords())) {
            for (ProductDTO record : dtoPage.getRecords()) {
                ProductPageVO vo = new ProductPageVO();
                BeanUtils.copyProperties(record, vo);
                resultList.add(vo);
            }
        }
        result.setRecords(resultList);
        return result;
    }

    @Override
    public RestResult create(ProductDTO dto) {
        //添加商品信息
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setPutaway(Boolean.TRUE);
        entity.setUsable(Boolean.TRUE);
        entity.setDeleted(Boolean.FALSE);
        entity.setCreateTime(Calendar.getInstance().getTime());
        entity.setHits(0);

        this.save(entity);

        //添加商品明细
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        productDetailEntity.setProductId(entity.getId());
        productDetailService.save(productDetailEntity);
        //添加销售属性值

        return RestResult.success();
    }

    @Override
    public RestResult update(Long productId, ProductDTO dto) {
        //修改商品信息
        ProductEntity entity = this.getById(productId);
        BeanUtils.copyProperties(dto, entity);
        productImgService.remove(Wrappers.<ProductImgEntity>lambdaQuery()
                .eq(ProductImgEntity::getForeignId, String.valueOf(productId))
                .eq(ProductImgEntity::getTypeCode, ProductImgEntity.TYPE_CODE_PRODUCT));

        Boolean resultProduct = this.save(entity);
        //修改商品明细
        productDetailService.remove(Wrappers.<ProductDetailEntity>lambdaQuery()
                .eq(ProductDetailEntity::getId, productId));
        ProductDetailEntity productDetailEntity = new ProductDetailEntity();
        productDetailEntity.setProductId(productId);
        productDetailService.save(productDetailEntity);
        //修改销售属性值
        //获取商品销售属性值
        ProductDTO isSaleParam = new ProductDTO();
        List<ProductPropertyDTO> propertyIsSaleList = productMapper.findPropertyIsSale(isSaleParam);
        //判断之前是否存在，存在则不操作，不存在则新增
        //判断之前的值是否被删除，删除需要删除对应的SKU信息
        return RestResult.success();
    }

    @Override
    public RestResult updateIsPutAway(UpdateIsPutawayParam param) {
        for (Integer id : param.getIds()) {
            ProductEntity entity = this.getById(id);
            entity.setPutaway(param.getIsPutaway());
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
        return null;
    }
}
