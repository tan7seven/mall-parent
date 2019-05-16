package com.mall.malladmin.service.product;

import com.mall.malladmin.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品信息
 */
public interface ProductService {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductEntity add(ProductEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductEntity> findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductEntity entity);
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
    List<ProductEntity> findList(ProductEntity entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    Page<ProductEntity> findPage(ProductEntity entity, Pageable page);
}
