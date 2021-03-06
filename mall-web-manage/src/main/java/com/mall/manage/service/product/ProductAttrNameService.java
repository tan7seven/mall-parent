package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.entity.product.ProductAttrNameEntity;
import com.mall.manage.model.param.product.attr.AttrCreateParam;
import com.mall.manage.model.param.product.attr.AttrShowedUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUpdateParam;
import com.mall.manage.model.param.product.attr.AttrUsableUpdateParam;

/**
 * 商品属性名
 */
public interface ProductAttrNameService extends IService<ProductAttrNameEntity> {

    /**
     * 新增
     * @return
     */
    Boolean createAttrName(AttrCreateParam param);
    /**
     * 更新
     */
    Boolean update(AttrUpdateParam param);
    /**
     * 修改是否显示
     * @return
     */
    Boolean updateIsShow(AttrShowedUpdateParam param);
    /**
     * 修改是否可用
     * @return
     */
    Boolean updateIsSale(AttrUsableUpdateParam param);
}
