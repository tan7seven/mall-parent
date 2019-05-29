package com.mall.malladmin.service.product;

import com.mall.malladmin.entity.product.ProductTypeEntity;
import com.mall.malladmin.vo.common.CommonCascaderVo;
import com.mall.malladmin.vo.product.ProductTypeVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 商品类目
 */
public interface ProductTypeService {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductTypeEntity add(ProductTypeEntity entity);

    /**
     * 修改
     * @param vo
     * @return
     */
    ProductTypeEntity update(ProductTypeVo vo);
    /**
     *
     * @param vo
     * @return
     */
    @Transactional
    ProductTypeEntity create(ProductTypeVo vo);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductTypeEntity> findById(Integer id);

    /**
     * 删除
     * @param entity
     */
    void delete(ProductTypeEntity entity);
    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductTypeEntity> findList(ProductTypeEntity entity);

    /**
     * 查询
     * @param entity
     * @return
     */
    Page<ProductTypeEntity> findPage(ProductTypeEntity entity, Pageable page);

    /**
     * 获取树结构
     * @return
     */
    List<CommonCascaderVo> getCascader();
}
