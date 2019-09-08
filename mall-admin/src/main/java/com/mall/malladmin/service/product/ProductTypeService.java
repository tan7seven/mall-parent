package com.mall.malladmin.service.product;

import com.mall.malladmin.dto.common.CommonCascaderDto;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductTypeDto;
import com.mall.malladmin.entity.product.ProductTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

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
     * 修改
     * @param dto
     * @return
     */
    ProductTypeEntity update(ProductTypeDto dto);
    /**
     * 修改是否可用
     * @param dto
     * @return
     */
    CommonResultDto updateIsUsable(ProductTypeDto dto);
    /**
     *
     * @param dto
     * @return
     */
    @Transactional
    ProductTypeEntity create(ProductTypeDto dto);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductTypeEntity> findById(Integer id);

    /**
     * 根据ID删除-逻辑删除
     * @param id
     */
    @Transactional
    void deleteById(Integer id);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductTypeEntity> findList(ProductTypeEntity entity);

    /**
     * 查询
     * @param dto
     * @return
     */
    Page<ProductTypeEntity> findPage(ProductTypeDto dto);

    /**
     * 获取树结构
     * @return
     */
    List<CommonCascaderDto> getCascader();
}
