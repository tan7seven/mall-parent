package com.mall.app.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.model.vo.product.ProductDetailVO;
import com.mall.app.service.product.ProductDetailService;
import com.mall.app.service.product.ProductService;
import com.mall.app.service.product.utils.ProductUtil;
import com.mall.common.enums.ImgTypeEnum;
import com.mall.common.enums.ResultStatus;
import com.mall.common.exception.BusinessException;
import com.mall.dao.entity.product.*;
import com.mall.dao.mapper.product.ProductAttrValueMapper;
import com.mall.dao.mapper.product.ProductImgMapper;
import com.mall.dao.mapper.product.ProductMapper;
import com.mall.dao.mapper.product.ProductSkuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service(value = "productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {

    @Autowired
    private ProductImgMapper productImgMapper;
    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductDetailService productDetailService;

    @Override
    public ProductDetailVO getProductDetail(Long productId) {
        ProductEntity product = this.getById(productId);
        if (Objects.isNull(product)) {
            throw new BusinessException(ResultStatus.BUS_MSG_NOT_FOUND);
        }
        List<ProductImgEntity> imgList = productImgMapper.selectList(Wrappers.<ProductImgEntity>lambdaQuery()
                .eq(ProductImgEntity::getForeignId, productId)
                .eq(ProductImgEntity::getTypeCode, ImgTypeEnum.PRODUCT.getCode()));
        List<ProductAttrValueEntity> attrValueList = productAttrValueMapper.selectList(Wrappers.<ProductAttrValueEntity>lambdaQuery()
                .eq(ProductAttrValueEntity::getProductId, productId));
        List<ProductSkuEntity> skuList = productSkuMapper.selectList(Wrappers.<ProductSkuEntity>lambdaQuery()
                .eq(ProductSkuEntity::getProductId, productId));
        ProductDetailEntity productDetail = productDetailService.getOne(Wrappers.<ProductDetailEntity>lambdaQuery().eq(ProductDetailEntity::getProductId, productId));

        ProductDetailVO result = ProductUtil.buildProductDetail(product, imgList, skuList, attrValueList, productDetail);
        return result;
    }
}
