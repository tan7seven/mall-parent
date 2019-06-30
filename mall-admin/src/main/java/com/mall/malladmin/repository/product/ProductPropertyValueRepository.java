package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductPropertyValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValueEntity, Integer>, JpaSpecificationExecutor<ProductPropertyValueEntity> {
    /**
     * 根据商品ID获取属性值
     * @param id
     * @return
     */
    List<ProductPropertyValueEntity> findByProductId(Integer id);
    /**
     * 根据商品ID获取属性值
     * @return
     */
    List<ProductPropertyValueEntity> findByPropertyNameIdAndProductId(Integer propertyNameId, Integer productId);
}
