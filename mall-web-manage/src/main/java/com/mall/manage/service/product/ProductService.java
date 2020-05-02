package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductDTO;
import com.mall.dao.entity.product.ProductEntity;
import com.mall.manage.model.param.product.product.CreateParam;
import com.mall.manage.model.param.product.product.UpdateIsPutawayParam;
import com.mall.manage.model.param.product.product.UpdateParam;
import com.mall.manage.model.vo.product.product.ProductDetailVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息
 */
public interface ProductService extends IService<ProductEntity> {
    /**
     * 创建商品信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean createProduct(CreateParam param);
    /**
     * 修改商品信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean updateProduct(UpdateParam param);

    /**
     * 更新上下架状态
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    Boolean updateIsPutAway(UpdateIsPutawayParam param);
    /**
     * 获取商品详情
     * @param id
     * @return
     */
    ProductDetailVO getProductDetail(Long id);
//    todo

    /**
     * 批量删除
     */
    @Transactional
    Boolean deleteList(List<Integer>  ids);

    /**
     * mybatis分页查询
     * @returndto
     */
    Page<ProductDTO> findPage(Long typeId, String productName, Boolean putaway, Integer pageNum, Integer pageSize);


    /**
     * 根据商品名称查询
     * @param name
     * @return
     */
    List<ProductDTO> findByName(String name);
}
