package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.entity.product.ProductDetailEntity;

import java.util.List;
import java.util.Optional;

/**
 * 商品详情
 */
public interface ProductDetailService extends IService<ProductDetailEntity> {
    /**
     * 新增
     * @param entity
     * @return
     */
    Boolean add(ProductDetailEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductDetailEntity> getById(Integer id);

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
     * @return
     */
    Page<ProductDetailEntity> findPage(Page<ProductDetailEntity> page);
}
