package com.mall.malladmin.service.product;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.entity.product.ProductSkuEntity;
import com.mall.malladmin.vo.product.ProductSkuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 商品SKU
 */
public interface ProductSkuService {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductSkuEntity add(ProductSkuEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductSkuEntity> findById(Integer id);

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
     * @param vo
     * @return
     */
    PageInfo<ProductSkuVo> findPage(ProductSkuVo vo);
}
