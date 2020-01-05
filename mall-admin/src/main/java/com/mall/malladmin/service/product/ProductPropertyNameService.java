package com.mall.malladmin.service.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.product.ProductPropertyNameDTO;
import com.mall.malladmin.entity.product.ProductPropertyNameEntity;

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
    CommonResultDTO add(ProductPropertyNameEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductPropertyNameEntity> findById(Integer id);
    /**
     * 更新
     * @param dto
     */
    CommonResultDTO update(ProductPropertyNameDTO dto);

    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据typeId删除
     * @param typeId
     */
    void updateIsDeleteByTypeId(Integer typeId);

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
     * @param dto
     * @return
     */
    PageInfo<ProductPropertyNameDTO> findPage(ProductPropertyNameDTO dto);

    /**
     * 修改是否销售属性
     * @param dto
     * @return
     */
    CommonResultDTO updateIsSale(ProductPropertyNameDTO dto);

    /**
     * 修改是否显示
     * @param dto
     * @return
     */
    CommonResultDTO updateIsShow(ProductPropertyNameDTO dto);

}
