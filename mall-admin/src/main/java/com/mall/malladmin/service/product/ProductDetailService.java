package com.mall.malladmin.service.product;

import com.mall.malladmin.entity.product.ProductDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品详情
 */
public interface ProductDetailService {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductDetailEntity add(ProductDetailEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductDetailEntity> findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductDetailEntity entity);
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
    List<ProductDetailEntity> findList(ProductDetailEntity entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    Page<ProductDetailEntity> findPage(ProductDetailEntity entity,Pageable page);
}
