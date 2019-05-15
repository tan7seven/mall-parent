package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValueEntity, Integer>, JpaSpecificationExecutor<ProductPropertyValueEntity> {
}
