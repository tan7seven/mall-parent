package com.mall.mallwechar.repository.product;

import com.mall.mallmodel.entity.product.ProductImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImgRepository extends JpaRepository<ProductImgEntity, Integer>, JpaSpecificationExecutor<ProductImgEntity> {
}
