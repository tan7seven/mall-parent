package com.mall.malladmin.service.system;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.common.TreeDto;
import com.mall.malladmin.dto.system.MenuDto;
import com.mall.malladmin.entity.system.MenuEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单信息service
 */
public interface MenuService {
    /**
     * 新增
     * @param dto
     * @return
     */
    @Transactional
    MenuEntity add(MenuDto dto);
    /**
     * 修改
     * @param dto
     * @return
     */
    @Transactional
    void update(MenuDto dto, String id);
    /**
     * 获取
     * @param dto
     * @return
     */
    List<MenuEntity> getList(MenuDto dto);
    /**
     * 获取
     * @param dto
     * @return
     */
    PageInfo<MenuDto> getPage(MenuDto dto);

    /**
     * 获取菜单树
     * @return
     */
    List<TreeDto> getMenuTree();

    /**
     * 根据父节点获取菜单列表
     * @param menuId
     * @return
     */
    List<MenuDto> getListById(String menuId);
    /**
     *  根据ID查找
     * @param menuId
     * @return
     */
    MenuDto findById(String menuId);
    /**
     * 删除（逻辑删除）
     * @param ids
     */
    void deleteMenu(String[] ids);

    /**
     * 是否隐藏
     * @param dto
     */
    void updateIsHidden(MenuDto dto);
}
