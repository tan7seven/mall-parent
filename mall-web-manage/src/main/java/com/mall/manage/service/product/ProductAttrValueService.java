package com.mall.manage.service.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.entity.product.ProductAttrValueEntity;

import java.util.List;
import java.util.Optional;

/**
 * 商品属性值
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductAttrValueEntity add(ProductAttrValueEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductAttrValueEntity> getById(Long id);

    /**
     * 根据商品ID获取属性值信息
     * @param productId
     * @return
     */
    List<ProductAttrValueEntity> findByProductId(Long productId);
    /**
     * 删除
     * @param entity
     */
    void delete(ProductAttrValueEntity entity);
    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductAttrValueEntity> findList(ProductAttrValueEntity entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    IPage<ProductAttrValueEntity> findPage(ProductAttrValueEntity entity, IPage page);
}
