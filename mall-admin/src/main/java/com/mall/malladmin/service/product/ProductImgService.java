package com.mall.malladmin.service.product;

import com.mall.malladmin.entity.product.ProductImgEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品图片
 */
public interface ProductImgService {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductImgEntity add(ProductImgEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductImgEntity> findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductImgEntity entity);
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
    List<ProductImgEntity> findList(ProductImgEntity entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    Page<ProductImgEntity> findPage(ProductImgEntity entity, Pageable page);
}
