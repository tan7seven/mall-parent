package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.manage.model.vo.product.sku.SkuCreateParam;
import com.mall.manage.model.vo.product.sku.SkuListVO;

import java.util.List;

/**
 * 商品SKU
 */
public interface ProductSkuService extends IService<ProductSkuEntity> {
    /**
     * 分页查询
     * @return
     */
    Page<ProductSkuDTO> findPage(Integer pageNum, Integer pageSize);
    /**
     * 新增
     * @return
     */
    Boolean createSku(SkuCreateParam param);
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
     * 根据商品ID获取SKU列表
     * @param productId
     * @return
     */
    List<SkuListVO> getSkuByProductId(Long productId);

}
