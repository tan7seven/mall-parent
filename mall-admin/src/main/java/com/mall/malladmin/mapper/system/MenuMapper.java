package com.mall.malladmin.mapper.system;

import com.mall.malladmin.dto.common.TreeDto;
import com.mall.malladmin.dto.system.MenuDto;
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
    List<MenuDto> getList(@Param("dto") MenuDto dto);
    /**
     * 根据父节点获取列表
     * @param menuId
     * @return
     */
    List<MenuDto> getListById(@Param("menuId") String menuId);

    /**
     * 获取菜单树
     * @return
     */
    List<TreeDto> getMenuTree();
}
