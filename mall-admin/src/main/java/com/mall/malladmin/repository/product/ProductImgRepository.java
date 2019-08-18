package com.mall.malladmin.repository.product;

import com.mall.malladmin.entity.product.ProductImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImgEntity, Integer>, JpaSpecificationExecutor<ProductImgEntity> {

    /**
     * 根据外键跟类型编码删除
     * @param foreignId
     */
    void deleteByForeignIdAndTypeCode(String foreignId, String typeCode);

    /**
     *根据外键跟类型编码查询
     */
    List<ProductImgEntity> findByForeignIdAndAndTypeCode(String foreignId, String typeCode);
}
