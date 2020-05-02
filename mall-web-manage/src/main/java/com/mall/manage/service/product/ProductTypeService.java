package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductTypeEntity;

import java.util.List;

/**
 * 商品类目
 */
public interface ProductTypeService extends IService<ProductTypeEntity> {
    /**
     * 新增
     * @param entity
     * @return
     */
    ProductTypeEntity add(ProductTypeEntity entity);
    /**
     * 分页查询
     * @return
     */
    Page<ProductTypeEntity> findPage(Long parentId, String typeName, Integer page, Integer pageSize);
    /**
     * 获取树结构
     * @return
     */
    List<CommonCascaderDTO> getCascader();
}
