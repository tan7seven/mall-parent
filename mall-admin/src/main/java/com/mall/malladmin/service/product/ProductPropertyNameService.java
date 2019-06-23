package com.mall.malladmin.service.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import com.mall.malladmin.vo.common.CommonResultVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品属性名
 */
public interface ProductPropertyNameService {
    /**
     * 新增
     * @param entity
     * @return
     */
    CommonResultVo add(ProductPropertyNameEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductPropertyNameEntity> findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductPropertyNameEntity entity);
    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据typeId删除
     * @param typeId
     */
    void deleteByTypeId(Integer typeId);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductPropertyNameEntity> findList(ProductPropertyNameEntity entity);

    /**
     * 根据typeId获取
     * @param typeId
     * @return
     */
    List<ProductPropertyNameEntity> findByTypeId(Integer typeId);
    /**
     * 查询
     * @param entity
     * @return
     */
    Page<ProductPropertyNameEntity> findPage(ProductPropertyNameEntity entity, Pageable page);
}
