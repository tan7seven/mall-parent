package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImgRepository extends JpaRepository<ProductImgEntity, Integer>, JpaSpecificationExecutor<ProductImgEntity> {
}
