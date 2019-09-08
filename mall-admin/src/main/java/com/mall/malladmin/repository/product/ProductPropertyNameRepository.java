package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductPropertyNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPropertyNameRepository extends JpaRepository<ProductPropertyNameEntity, Integer>, JpaSpecificationExecutor<ProductPropertyNameEntity> {
    /**
     * 根据typeId获取
     * @param typeId
     * @return
     */
    List<ProductPropertyNameEntity> findByTypeIdAndIsDelete(Integer typeId, String idDelete);
    /**
     * 根据typeId和isSale获取
     * @param typeId
     * @return
     */
    List<ProductPropertyNameEntity> findByTypeIdAndIsSaleAndIsDelete(Integer typeId, String isSale, String isDelete);
    /**
     * 根据typeId和name获取
     * @param typeId
     * @return
     */
    List<ProductPropertyNameEntity> findByTypeIdAndNameAndIsDelete(Integer typeId, String Name, String isDelete);
    /**
     * 根据typeId删除
     * @param deleteByTypeId
     */
    @Modifying
    @Query(value = "update mall_product_property_name SET is_delete = '1' where type_id=?1",nativeQuery=true)
    void updateIsDeleteByTypeId(Integer deleteByTypeId);
}
