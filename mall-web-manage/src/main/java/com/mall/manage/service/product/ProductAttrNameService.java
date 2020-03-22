package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.product.ProductAttrNameDTO;
import com.mall.dao.entity.product.ProductAttrNameEntity;

import java.util.List;

/**
 * 商品属性名
 */
public interface ProductAttrNameService extends IService<ProductAttrNameEntity> {
    /**
     * 新增
     * @param entity
     * @return
     */
    RestResult add(ProductAttrNameEntity entity);
    /**
     * 更新
     * @param dto
     */
    RestResult update(ProductAttrNameDTO dto);

    /**
     * 根据逐渐删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据typeId删除
     * @param typeId
     */
    void updateIsDeleteByTypeId(Long typeId);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductAttrNameEntity> findList(ProductAttrNameEntity entity);

    /**
     * 根据typeId获取
     * @param typeId
     * @return
     */
    List<ProductAttrNameEntity> findByTypeId(Integer typeId);
    /**
     * 查询
     * @param dto
     * @return
     */
    Page<ProductAttrNameDTO> findPage(ProductAttrNameDTO dto);

    /**
     * 修改是否销售属性
     * @param dto
     * @return
     */
    RestResult updateIsSale(ProductAttrNameDTO dto);

    /**
     * 修改是否显示
     * @param dto
     * @return
     */
    RestResult updateIsShow(ProductAttrNameDTO dto);

}
