package com.mall.mallwechar.repository.product;

import com.mall.mallmodel.entity.product.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer>, JpaSpecificationExecutor<ProductTypeEntity> {
    /**
     * 根据parentId删除
     * @param parentId
     */
    @Transactional
    @Modifying
    @Query(value = "delete  from mall_product_type  where parent_id=?1",nativeQuery=true)
    void deleteByParentId(Integer parentId);

    /**
     * 根据parentID查找
     * @param parentId
     * @return
     */
    List<ProductTypeEntity> findAllByParentId(Integer parentId);
}
