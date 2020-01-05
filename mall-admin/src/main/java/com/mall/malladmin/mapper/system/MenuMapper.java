package com.mall.malladmin.mapper.system;

import com.mall.malladmin.dto.common.TreeDTO;
import com.mall.malladmin.dto.system.MenuAuthorityDTO;
import com.mall.malladmin.dto.system.MenuDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单列表
 */
public interface MenuMapper {

    /**
     * 获取列表
     * @param dto
     * @return
     */
    List<MenuDTO> getList(@Param("dto") MenuDTO dto);
    /**
     * 根据父节点获取列表
     * @param menuId
     * @return
     */
    List<MenuDTO> getListById(@Param("menuId") String menuId);

    /**
     * 获取菜单树
     * @return
     */
    List<TreeDTO> getMenuTree(@Param("dto") MenuAuthorityDTO dto);
}
