package com.mall.malladmin.service.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.entity.product.ProductSkuEntity;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.product.ProductSkuDTO;
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
    CommonResultDTO add(ProductSkuDTO dto);
    /**
     * 更新
     * @return
     */
    CommonResultDTO update(Integer id, ProductSkuDTO dto);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    ProductSkuDTO findById(Integer id);
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
    PageInfo<ProductSkuDTO> findPage(ProductSkuDTO dto);
}
