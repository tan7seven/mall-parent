package com.mall.malladmin.service.product;

import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品属性值
 */
public interface ProductPropertyValueService {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductPropertyValueEntity add(ProductPropertyValueEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductPropertyValueEntity> findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductPropertyValueEntity entity);
    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductPropertyValueEntity> findList(ProductPropertyValueEntity entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    Page<ProductPropertyValueEntity> findPage(ProductPropertyValueEntity entity, Pageable page);
}
