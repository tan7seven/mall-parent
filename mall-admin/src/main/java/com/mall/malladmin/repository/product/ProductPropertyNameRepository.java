package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductPropertyNameRepository extends JpaRepository<ProductPropertyNameEntity, Integer>, JpaSpecificationExecutor<ProductPropertyNameEntity> {
    /**
     * 根据typeId获取
     * @param typeId
     * @return
     */
    List<ProductPropertyNameEntity> findByTypeId(Integer typeId);

    /**
     * 根据typeId删除
     * @param deleteByTypeId
     */
    @Transactional
    @Modifying
    @Query(value = "delete  from mall_product_property_name  where type_id=?1",nativeQuery=true)
    void deleteByTypeId(Integer deleteByTypeId);
}
