package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer>, JpaSpecificationExecutor<ProductTypeEntity> {
}
