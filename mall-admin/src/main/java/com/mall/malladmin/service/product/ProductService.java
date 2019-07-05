package com.mall.malladmin.service.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.entity.product.ProductEntity;
import com.mall.malladmin.vo.common.CommonResultVo;
import com.mall.malladmin.vo.product.ProductVo;
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
    ProductVo findById(Integer id);
    /**
     * 批量删除
     */
    @Transactional
    CommonResultVo deleteList(Integer[] ids);
    /**
     * 删除
     * @param entity
     */
    void delete(ProductEntity entity);
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
     * @returnvo
     */
    PageInfo<ProductVo> findPage(ProductVo vo);

    /**
     * 创建商品信息
     * @param vo
     * @return
     */
    @Transactional
    CommonResultVo create(ProductVo vo);
    /**
     * 更新商品信息
     * @param vo
     * @return
     */
    @Transactional
    CommonResultVo update(Integer id, ProductVo vo);
    /**
     * 更新上下架状态
     * @return
     */
    @Transactional
    CommonResultVo updateIsPutAway(String isPutaway, Integer[] ids);
    /**
     * 根据商品名称查询
     * @param name
     * @return
     */
    List<ProductVo> findByName(String name);
}
