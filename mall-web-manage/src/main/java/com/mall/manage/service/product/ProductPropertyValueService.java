package com.mall.manage.service.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.entity.product.ProductPropertyValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品属性值
 */
public interface ProductPropertyValueService extends IService<ProductPropertyValueEntity> {
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
     * 根据商品ID获取属性值信息
     * @param productId
     * @return
     */
    List<ProductPropertyValueEntity> findByProductId(Integer productId);
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
    IPage<ProductPropertyValueEntity> findPage(ProductPropertyValueEntity entity, IPage page);
}
