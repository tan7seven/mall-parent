package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductPropertyNameDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;

import java.util.List;
import java.util.Optional;

/**
 * 商品属性名
 */
public interface ProductPropertyNameService extends IService<ProductPropertyNameEntity> {
    /**
     * 新增
     * @param entity
     * @return
     */
    RestResult add(ProductPropertyNameEntity entity);
    /**
     *  根据ID查找
     * @param id
     * @return
     */
    Optional<ProductPropertyNameEntity> findById(Integer id);
    /**
     * 更新
     * @param dto
     */
    RestResult update(ProductPropertyNameDTO dto);

    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据typeId删除
     * @param typeId
     */
    void updateIsDeleteByTypeId(Integer typeId);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductPropertyNameEntity> findList(ProductPropertyNameEntity entity);

    /**
     * 根据typeId获取
     * @param typeId
     * @return
     */
    List<ProductPropertyNameEntity> findByTypeId(Integer typeId);
    /**
     * 查询
     * @param dto
     * @return
     */
    Page<ProductPropertyNameDTO> findPage(ProductPropertyNameDTO dto);

    /**
     * 修改是否销售属性
     * @param dto
     * @return
     */
    RestResult updateIsSale(ProductPropertyNameDTO dto);

    /**
     * 修改是否显示
     * @param dto
     * @return
     */
    RestResult updateIsShow(ProductPropertyNameDTO dto);

}
