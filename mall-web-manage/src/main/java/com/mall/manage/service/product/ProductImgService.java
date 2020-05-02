package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.enums.ImgTypeEnum;
import com.mall.dao.entity.product.ProductImgEntity;

import java.util.List;

/**
 * 商品图片
 */
public interface ProductImgService extends IService<ProductImgEntity> {

    void saveOrUpdateImg(List<String> picUrlList, Long foreignId, ImgTypeEnum imgTypeEnum);
}
