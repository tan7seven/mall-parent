package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.product.ProductSkuDTO;
import com.mall.dao.entity.product.ProductSkuEntity;
import com.mall.manage.model.param.product.sku.SkuCreateParam;
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
    Page<ProductSkuDTO> findPage(String productName, Integer pageNum, Integer pageSize);
    /**
     * 新增
     * @return
     */
    Boolean createSku(SkuCreateParam param);
    /**
     * 根据商品ID获取SKU列表
     * @param productId
     * @return
     */
    List<SkuListVO> getSkuByProductId(Long productId);

}
