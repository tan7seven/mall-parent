package com.mall.dao.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.common.CommonCascaderDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.product.ProductTypeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品类目信息
 */
public interface ProductTypeMapper extends BaseMapper<ProductTypeEntity> {
    /**
     * 获取商品类目的联级信息
     * @return
     */
    List<CommonCascaderDTO> getCascader();

    /**
     * 根据父类ID更新usable字段
     * @param parentId
     * @param isUsable
     * @return
     */
    int updateUsableByParent(@Param("parentId") Integer parentId,@Param("isUsable") Integer isUsable);

}
