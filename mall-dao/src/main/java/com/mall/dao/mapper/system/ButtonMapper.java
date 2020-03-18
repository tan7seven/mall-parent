package com.mall.dao.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.dto.system.ButtonDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.system.ButtonEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单列表
 */
public interface ButtonMapper extends BaseMapper<ButtonEntity> {

    /**
     * 获取列表-已分配权限
     * @param dto
     * @return
     */
    List<ButtonDTO> getListAuthority(@Param("dto") MenuDTO dto);

    /**
     * 根据用户编码跟菜单ID删除
     * @param dto
     * @return
     */
    int deleteByMenuIdAndUserId(@Param("dto") AdminDTO dto);

}
