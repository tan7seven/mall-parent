package com.mall.manage.service.system.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.enums.ButtonEnum;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.MenuAuthorityDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.ButtonEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.dao.mapper.system.MenuMapper;
import com.mall.manage.model.param.system.MenuCreateParam;
import com.mall.manage.service.system.ButtonService;
import com.mall.manage.service.system.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private ButtonService buttonService;

    @Override
    public MenuEntity addMenuAndButton(MenuCreateParam param) {
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
        return entity;
    }

    @Override
    public void update(MenuDTO dto, Long id) {
        MenuEntity entity = this.getById(id);
        entity.setHidden(dto.getIsHidden());
        entity.setMenuCode(dto.getMenuCode());
        entity.setMenuIcon(dto.getMenuIcon());
        entity.setMenuTitle(dto.getMenuTitle());
        entity.setMenuUrl(dto.getMenuUrl());
        entity.setParentId(dto.getParentId());
        this.save(entity);
        this.checkButton(dto, id);
    }

    @Override
    public List<TreeDTO> getMenuTree(Long userId) {
        return menuMapper.getMenuTree(userId);
    }

    @Override
    public void updateIsHidden(MenuDTO dto) {
    }

    /**
     * 修改菜单信息时
     * 1、验证哪些按钮是新增
     * 2、验证哪些按钮被删除
     */
    private void checkButton(MenuDTO dto, Long id){
        List<ButtonEntity> buttonEntityList = buttonService.list(Wrappers.<ButtonEntity>lambdaQuery().eq(ButtonEntity::getMenuId, id));
        List<String> buttonList = dto.getButtonList();
        //1、验证哪些按钮是新增
        buttonList.forEach(s -> {
            List<String> buttonNameList = buttonEntityList.stream().map(m -> m.getButtonName()).collect(Collectors.toList());
            if (buttonNameList.indexOf(s) == -1) {
                ButtonEntity buttonEntity = new ButtonEntity();
                buttonEntity.setButtonName(s);
                buttonEntity.setButtonCode(ButtonEnum.getKey(s));
                buttonEntity.setMenuId(id);
                buttonService.save(buttonEntity);
            }
        });
        //2、验证哪些按钮被删除
        buttonEntityList.forEach(s -> {
            if (buttonList.indexOf(s.getButtonName()) == -1) {
            }
        });
    }


}
