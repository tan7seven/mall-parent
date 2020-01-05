package com.mall.malladmin.service.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.entity.product.ProductEntity;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.product.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    ProductDTO findById(Integer id);
    /**
     * 批量删除
     */
    @Transactional
    CommonResultDTO deleteList(List<Integer>  ids);
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

    /**
     * mybatis分页查询
     * @returndto
     */
    PageInfo<ProductDTO> findPage(ProductDTO dto);

    /**
     * 创建商品信息
     * @param dto
     * @return
     */
    @Transactional
    CommonResultDTO create(ProductDTO dto);
    /**
     * 更新商品信息
     * @param dto
     * @return
     */
    @Transactional
    CommonResultDTO update(Integer id, ProductDTO dto);
    /**
     * 更新上下架状态
     * @return
     */
    @Transactional
    CommonResultDTO updateIsPutAway(String isPutaway, List<Integer>  ids);
    /**
     * 根据商品名称查询
     * @param name
     * @return
     */
    List<ProductDTO> findByName(String name);
}
