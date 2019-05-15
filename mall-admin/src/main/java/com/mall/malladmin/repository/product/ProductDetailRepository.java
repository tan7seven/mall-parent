package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer>, JpaSpecificationExecutor<ProductDetailEntity> {
}
