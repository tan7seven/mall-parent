package com.mall.manage.controller.system.utils;

import com.google.common.collect.Lists;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.manage.model.vo.system.MenuVO;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/29
 */
public class MenuUtil {
    public static List<MenuVO> getVOList(List<MenuDTO> dtoList){
        List<MenuVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dtoList)) {
            return result;
        }
        for (MenuDTO menuDTO : dtoList) {
            MenuVO vo = buildMenuVO(menuDTO);
            vo.setChildren(getVOList(menuDTO.getChildren()));
            result.add(vo);
        }
        return result;
    }


    public static MenuVO buildMenuVO(MenuDTO dto){
        MenuVO result = new MenuVO();
        result.setHasChildren(dto.getHasChildren());
        result.setMenuCode(dto.getMenuCode());
        result.setMenuId(dto.getMenuId());
        result.setMenuTitle(dto.getMenuTitle());
        result.setMenuUrl(dto.getMenuUrl());
        result.setParentId(dto.getParentId());
        result.setParentTitle(dto.getParentTitle());
        return result;
    }
}
