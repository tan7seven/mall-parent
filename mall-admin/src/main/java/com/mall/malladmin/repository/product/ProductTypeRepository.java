package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer>, JpaSpecificationExecutor<ProductTypeEntity> {
    /**
     * 根据parentId删除
     * @param parentId
     */
    @Modifying
    @Query(value = "UPDATE  mall_product_type SET  is_delete = '1' where parent_id=?1",nativeQuery=true)
    void updateIsDeleteByParentId(Integer parentId);

    /**
     * 逻辑删除
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE  mall_product_type SET  is_delete = '1' where type_id=?1",nativeQuery=true)
    void updateIsDelete(Integer id);
    /**
     * 根据parentID查找
     * @param parentId
     * @return
     */
    List<ProductTypeEntity> findAllByParentId(Integer parentId);
}
