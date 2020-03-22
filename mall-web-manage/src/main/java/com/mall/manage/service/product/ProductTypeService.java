package com.mall.manage.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.vo.RestPage;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.dto.product.ProductTypeDTO;
import com.mall.dao.entity.product.ProductTypeEntity;
import com.mall.manage.model.vo.product.type.ProductTypePageVO;
import org.springframework.transaction.annotation.Transactional;

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
     * 修改
     * @param dto
     * @return
     */
    ProductTypeEntity update(ProductTypeDTO dto);
    /**
     * 修改是否可用
     * @param dto
     * @return
     */
    RestResult updateIsUsable(ProductTypeDTO dto);
    /**
     *
     * @param dto
     * @return
     */
    @Transactional
    ProductTypeEntity create(ProductTypeDTO dto);

    /**
     * 根据ID删除-逻辑删除
     * @param id
     */
    @Transactional
    void deleteById(Long id);

    /**
     * 查询
     * @param entity
     * @return
     */
    List<ProductTypeEntity> findList(ProductTypeEntity entity);

    /**
     * 分页查询
     * @return
     */
    RestPage<ProductTypePageVO> findPage(Long id, String typeName, Integer page, Integer pageSize);

    /**
     * 获取树结构
     * @return
     */
    List<CommonCascaderDTO> getCascader();
}
