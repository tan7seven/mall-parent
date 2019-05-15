package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductSkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSkuRepository extends JpaRepository<ProductSkuEntity, Integer>, JpaSpecificationExecutor<ProductSkuEntity> {
}
