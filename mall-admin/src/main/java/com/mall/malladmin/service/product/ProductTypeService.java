package com.mall.malladmin.service.product;

import com.mall.malladmin.entity.product.ProductTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目
 */
public interface ProductTypeService {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductTypeEntity add(ProductTypeEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductTypeEntity> findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductTypeEntity entity);
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
    List<ProductTypeEntity> findList(ProductTypeEntity entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    Page<ProductTypeEntity> findPage(ProductTypeEntity entity, Pageable page);
}
