package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer>, JpaSpecificationExecutor<ProductDetailEntity> {
    /**
     * 根据商品ID删除
     * @param id
     */
    void deleteByProductId(Integer id);

    /**
     * 根据商品ID查询
     * @param id
     * @return
     */
    List<ProductDetailEntity> findByProductId(Integer id);
}
