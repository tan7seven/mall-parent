package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.manage.param.product.product.GetPageParam;
import com.mall.manage.param.product.product.UpdateIsPutawayParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductService extends IService<ProductEntity> {
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
    ResultPage<ProductDTO> findPage(GetPageParam param);

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
    RestResult updateIsPutAway(UpdateIsPutawayParam param);
    /**
     * 根据商品名称查询
     * @param name
     * @return
     */
    List<ProductDTO> findByName(String name);
}
