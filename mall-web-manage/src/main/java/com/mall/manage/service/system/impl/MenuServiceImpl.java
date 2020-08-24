package com.mall.manage.service.system.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.enums.ButtonEnum;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.entity.system.ButtonEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.dao.mapper.system.MenuMapper;
import com.mall.manage.model.param.system.menu.MenuCreateParam;
import com.mall.manage.model.param.system.menu.MenuUpdateParam;
import com.mall.manage.service.system.ButtonService;
import com.mall.manage.service.system.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ButtonService buttonService;

    @Override
    public Boolean addMenuAndButton(MenuCreateParam param) {
        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(param, entity);
        this.save(entity);
        List<String> buttonList = param.getButtonList();
        if (!buttonList.isEmpty()) {
            List<ButtonEntity> buttonEntityList = Lists.newArrayList();
            buttonList.forEach(s -> {
                ButtonEntity buttonEntity = new ButtonEntity();
                String buttonCode = ButtonEnum.getKey(s);
                buttonEntity.setButtonCode(buttonCode);
                buttonEntity.setButtonName(s);
                buttonEntity.setMenuId(entity.getId());
                buttonEntityList.add(buttonEntity);
            });
            buttonService.saveBatch(buttonEntityList);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateMenuAndButton(MenuUpdateParam param) {
        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setId(param.getMenuId());
        this.saveOrUpdate(entity);
        buttonService.remove(Wrappers.<ButtonEntity>lambdaQuery().eq(ButtonEntity::getMenuId, param.getMenuId()));
        if (!param.getButtonList().isEmpty()) {
            List<ButtonEntity> buttonEntityList = Lists.newArrayList();
            param.getButtonList().forEach(s -> {
                ButtonEntity buttonEntity = new ButtonEntity();
                String buttonCode = ButtonEnum.getKey(s);
                buttonEntity.setButtonCode(buttonCode);
                buttonEntity.setButtonName(s);
                buttonEntity.setMenuId(entity.getId());
                buttonEntityList.add(buttonEntity);
            });
            buttonService.saveBatch(buttonEntityList);
        }
        return Boolean.TRUE;
    }


    @Override
    public List<TreeDTO> getMenuTree(Long userId) {
        return menuMapper.getMenuTree(userId);
    }

}
