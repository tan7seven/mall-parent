package com.mall.dao.repository.product;

import com.mall.dao.entity.product.ProductPropertyValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValueEntity, Integer>, JpaSpecificationExecutor<ProductPropertyValueEntity> {

    /**
     * 根据商品编号和是否销售属性删除
     * @param productId
     * @param isSale
     */
    void deleteByProductIdAndIsSale(Integer productId, Integer isSale);

    /**
     * 删除属性值
     * @param isSale
     * @param value
     * @param productID
     */
    void deleteByIsSaleAndValueAndProductId(Integer isSale, String value, Integer productID);
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
