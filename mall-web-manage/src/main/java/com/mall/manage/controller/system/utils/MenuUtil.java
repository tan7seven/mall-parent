package com.mall.manage.controller.system.utils;

import com.google.common.collect.Lists;
import com.mall.dao.entity.system.ButtonAuthorityEntity;
import com.mall.dao.entity.system.ButtonEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.manage.model.vo.system.ButtonListVO;
import com.mall.manage.model.vo.system.MenuDetailVO;
import com.mall.manage.model.vo.system.MenuListVO;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/29
 */
public class MenuUtil {
    /**
     * 根据上级ID获取下级列表
     */
    public static List<MenuListVO> getVOListEntity(List<MenuEntity> dtoList){
        List<MenuListVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dtoList)) {
            return result;
        }
        for (MenuEntity entity : dtoList) {
            MenuListVO vo = buildMenuVO(entity);
            result.add(vo);
        }
        return result;
    }
    private static MenuListVO buildMenuVO(MenuEntity dto){
        MenuListVO result = new MenuListVO();
        result.setMenuCode(dto.getMenuCode());
        result.setMenuTitle(dto.getMenuTitle());
        result.setMenuUrl(dto.getMenuUrl());
        result.setParentId(dto.getParentId());
        result.setMenuId(dto.getId());
        result.setHidden(dto.getHidden());
        return result;
    }

    /**
     * 获取按钮列表
     */
    public static  ButtonListVO buildButtonListVO(List<ButtonAuthorityEntity> authorityEntityList){
        ButtonListVO result = new ButtonListVO();
        if (!CollectionUtils.isEmpty(authorityEntityList)) {
            List<ButtonListVO.ButtonListAuth> buttonListAuth = Lists.newArrayList();
            for (ButtonAuthorityEntity authorityEntity : authorityEntityList) {
                ButtonListVO.ButtonListAuth auth = new ButtonListVO.ButtonListAuth();
                auth.setButtonCode(authorityEntity.getButtonCode());
                buttonListAuth.add(auth);
            }
            result.setButtonListAuthority(buttonListAuth);
        }
        return result;
    }

    public static MenuDetailVO buildMenuDetailVO(MenuEntity menuEntity, List<ButtonEntity> buttonList){
        MenuDetailVO result = new MenuDetailVO();
        result.setHidden(menuEntity.getHidden());
        result.setMenuCode(menuEntity.getMenuCode());
        result.setMenuId(menuEntity.getId());
        result.setMenuTitle(menuEntity.getMenuTitle());
        result.setMenuUrl(menuEntity.getMenuUrl());
        result.setParentId(menuEntity.getParentId());
        result.setMenuIcon(menuEntity.getMenuIcon());
        if (!CollectionUtils.isEmpty(buttonList)) {
            List<MenuDetailVO.MenuDetailButtonVO> buttonVOList = Lists.newArrayList();
            for (ButtonEntity buttonEntity : buttonList) {
                MenuDetailVO.MenuDetailButtonVO buttonVO = new MenuDetailVO.MenuDetailButtonVO();
                buttonVO.setButtonName(buttonEntity.getButtonName());
                buttonVO.setId(buttonEntity.getId());
                buttonVOList.add(buttonVO);
            }
            result.setButtonList(buttonVOList);
        }
        return result;
    }
}
