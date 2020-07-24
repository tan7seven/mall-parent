package com.mall.manage.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.MenuAuthorityDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.manage.model.param.system.MenuCreateParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单信息service
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 新增
     */
    @Transactional
    MenuEntity addMenuAndButton(MenuCreateParam param);
    /**
     * 修改
     * @param dto
     * @return
     */
    @Transactional
    void update(MenuDTO dto, Long id);
    /**
     * 获取菜单树
     * @return
     */
    List<TreeDTO> getMenuTree(Long userId);


    /**
     * 是否隐藏
     * @param dto
     */
    void updateIsHidden(MenuDTO dto);

}
