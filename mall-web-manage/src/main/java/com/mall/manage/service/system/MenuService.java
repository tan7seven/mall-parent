package com.mall.manage.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.manage.model.param.system.menu.MenuCreateParam;
import com.mall.manage.model.param.system.menu.MenuUpdateParam;

import java.util.List;

/**
 * 菜单信息service
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 新增
     */
    Boolean addMenuAndButton(MenuCreateParam param);
    /**
     * 修改
     */
    Boolean updateMenuAndButton(MenuUpdateParam param);
    /**
     * 获取菜单树
     */
    List<TreeDTO> getMenuTree(Long userId);

}
