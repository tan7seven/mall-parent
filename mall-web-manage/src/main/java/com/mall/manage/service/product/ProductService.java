package com.mall.manage.service.product;

import com.github.pagehelper.PageInfo;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.manage.param.product.ProductGetPageParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductService {
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
    RestResult deleteList(List<Integer>  ids);

    /**
     * mybatis分页查询
     * @returndto
     */
    PageInfo<ProductDTO> findPage(ProductGetPageParam param);

    /**
     * 创建商品信息
     * @param dto
     * @return
     */
    @Transactional
    RestResult create(ProductDTO dto);
    /**
     * 更新商品信息
     * @param dto
     * @return
     */
    @Transactional
    RestResult update(Integer id, ProductDTO dto);
    /**
     * 更新上下架状态
     * @return
     */
    @Transactional
    RestResult updateIsPutAway(String isPutaway, List<Integer>  ids);
    /**
     * 根据商品名称查询
     * @param name
     * @return
     */
    List<ProductDTO> findByName(String name);
}
