package com.mall.manage.service.product.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.ImgTypeEnum;
import com.mall.dao.entity.product.ProductImgEntity;
import com.mall.dao.mapper.product.ProductImgMapper;
import com.mall.manage.service.product.ProductImgService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "productImgService")
public class ProductImgServiceImpl extends ServiceImpl<ProductImgMapper, ProductImgEntity> implements ProductImgService {
    @Override
    public void saveOrUpdateImg(List<String> picUrlList, Long foreignId, ImgTypeEnum imgTypeEnum) {
        if (CollectionUtils.isEmpty(picUrlList)) {
            return;
        }
        List<ProductImgEntity> imgList = this.list(Wrappers.<ProductImgEntity>lambdaQuery()
                .eq(ProductImgEntity::getForeignId, foreignId)
                .eq(ProductImgEntity::getTypeCode, imgTypeEnum.getValue()));

        /** 判断需要删除的img*/
        if (!CollectionUtils.isEmpty(imgList)) {
            List<Long> removeImgId = Lists.newArrayList();
            for (ProductImgEntity imgEntity : imgList) {
                if (picUrlList.contains(imgEntity.getImgUrl())) {
                    continue;
                }
                removeImgId.add(imgEntity.getId());
            }
            if (!CollectionUtils.isEmpty(removeImgId)) {
                this.baseMapper.deleteBatchIds(removeImgId);
            }
        }

        /** 判断需要新增的img*/
        List<ProductImgEntity> addImgEntity = Lists.newArrayList();
        List<String> oldImgList = imgList.stream().map(s -> s.getImgUrl()).collect(Collectors.toList());
        for (String picUrl : picUrlList) {
            if (oldImgList.contains(picUrl)) {
                continue;
            }
            ProductImgEntity addImg = new ProductImgEntity();
            addImg.setForeignId(foreignId);
            addImg.setImgUrl(picUrl.replaceAll(CommonConstant.IMG_PRE, ""));
            addImg.setTypeCode(imgTypeEnum.getCode());
            addImgEntity.add(addImg);
        }
        if (!CollectionUtils.isEmpty(addImgEntity)) {
            this.saveBatch(addImgEntity);
        }

    }
}
