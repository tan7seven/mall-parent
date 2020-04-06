package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductSkuEntity;

import java.util.List;

/**
 * 商品SKU
 */
public interface ProductSkuService extends IService<ProductSkuEntity> {
    /**
     * 新增
     * @return
     */
    RestResult add(ProductSkuDTO dto);
    /**
     * 更新
     * @return
     */
    RestResult update(Long id, ProductSkuDTO dto);
    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Integer id);
    /**
     * SpringJPA分页查询
     * @param entity
     * @return
     */
    Page<ProductSkuEntity> findPage(ProductSkuEntity entity, Page page);
    /**
     * mybatis-pagehelper分页查询
     * @param dto
     * @return
     */
    Page<ProductSkuDTO> findPage(ProductSkuDTO dto);
}
