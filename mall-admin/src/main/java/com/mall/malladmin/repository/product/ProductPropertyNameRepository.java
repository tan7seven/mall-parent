package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPropertyNameRepository extends JpaRepository<ProductPropertyNameEntity, Integer>, JpaSpecificationExecutor<ProductPropertyNameEntity> {
}
