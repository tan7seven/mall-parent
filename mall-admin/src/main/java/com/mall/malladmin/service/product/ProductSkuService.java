package com.mall.malladmin.service.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.entity.product.ProductSkuEntity;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.product.ProductSkuDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品SKU
 */
public interface ProductSkuService {
    /**
     * 新增
     * @return
     */
    CommonResultDto add(ProductSkuDto dto);
    /**
     * 更新
     * @return
     */
    CommonResultDto update(Integer id, ProductSkuDto dto);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    ProductSkuDto findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductSkuEntity entity);
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
    List<ProductSkuEntity> findList(ProductSkuEntity entity);

    /**
     * SpringJPA分页查询
     * @param entity
     * @return
     */
    Page<ProductSkuEntity> findPage(ProductSkuEntity entity, Pageable page);

    /**
     * mybatis-pagehelper分页查询
     * @param dto
     * @return
     */
    PageInfo<ProductSkuDto> findPage(ProductSkuDto dto);
}
