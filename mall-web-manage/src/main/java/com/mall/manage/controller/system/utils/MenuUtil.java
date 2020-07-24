package com.mall.manage.controller.system.utils;

import com.google.common.collect.Lists;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.ButtonAuthorityEntity;
import com.mall.dao.entity.system.ButtonEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.manage.model.vo.system.ButtonListVO;
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


    private static MenuVO buildMenuVO(MenuDTO dto){
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

    /**
     * 根据上级ID获取下级列表
     */
    public static List<MenuVO> getVOListEntity(List<MenuEntity> dtoList){
        List<MenuVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dtoList)) {
            return result;
        }
        for (MenuEntity entity : dtoList) {
            MenuVO vo = buildMenuVO(entity);
            result.add(vo);
        }
        return result;
    }
    private static MenuVO buildMenuVO(MenuEntity dto){
        MenuVO result = new MenuVO();
        result.setMenuCode(dto.getMenuCode());
        result.setMenuTitle(dto.getMenuTitle());
        result.setMenuUrl(dto.getMenuUrl());
        result.setParentId(dto.getParentId());
        result.setMenuId(dto.getId());
        return result;
    }

    public static  ButtonListVO buildButtonListVO(List<ButtonEntity> entityList, List<ButtonAuthorityEntity> authorityEntityList){
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        ButtonListVO result = new ButtonListVO();
        List<ButtonListVO.ButtonList> buttonList = Lists.newArrayList();
        for (ButtonEntity buttonEntity : entityList) {
            ButtonListVO.ButtonList vo = new ButtonListVO.ButtonList();
            vo.setButtonName(buttonEntity.getButtonName());
            vo.setId(buttonEntity.getId());
            buttonList.add(vo);
        }
        result.setButtonList(buttonList);
        if (!CollectionUtils.isEmpty(authorityEntityList)) {
            List<ButtonListVO.ButtonListAuth> buttonListAuth = Lists.newArrayList();
            for (ButtonAuthorityEntity authorityEntity : authorityEntityList) {
                ButtonListVO.ButtonListAuth auth = new ButtonListVO.ButtonListAuth();
                auth.setId(authorityEntity.getId());
                buttonListAuth.add(auth);
            }
            result.setButtonListAuthority(buttonListAuth);
        }
        return result;
    }
}
