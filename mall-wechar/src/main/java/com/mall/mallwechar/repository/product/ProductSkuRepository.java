package com.mall.mallwechar.repository.product;

import com.mall.mallmodel.entity.product.ProductSkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSkuRepository extends JpaRepository<ProductSkuEntity, Integer>, JpaSpecificationExecutor<ProductSkuEntity> {
    /**
     * 根据商品ID删除
     * @param productId
     */
    void deleteByProductId(Integer productId);
}
