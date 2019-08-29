package com.mall.malladmin.service.system.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.common.TreeDto;
import com.mall.malladmin.dto.system.MenuDto;
import com.mall.malladmin.entity.system.ButtonEntity;
import com.mall.malladmin.entity.system.MenuEntity;
import com.mall.malladmin.mapper.system.MenuMapper;
import com.mall.malladmin.repository.system.ButtonRepository;
import com.mall.malladmin.repository.system.MenuRepository;
import com.mall.malladmin.service.system.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mall.malladmin.util.MenuButtonMap.MENU_BUTTON_MAP;

@Service(value = "menuService")
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ButtonRepository buttonRepository;

    @Override
    public MenuEntity add(MenuDto dto) {
        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(dto, entity);
        MenuEntity resultEntity = menuRepository.save(entity);
        List<String> buttonList = dto.getButtonList();
        if (!buttonList.isEmpty()) {
            buttonList.forEach(s -> {
                ButtonEntity buttonEntity = new ButtonEntity();
                String buttonCode = MENU_BUTTON_MAP.get(s);
                buttonEntity.setButtonCode(buttonCode);
                buttonEntity.setButtonName(s);
                buttonEntity.setMenuId(resultEntity.getMenuId());
                buttonRepository.save(buttonEntity);
            });
        }
        return resultEntity;
    }

    @Override
    public void update(MenuDto dto, String id) {
        MenuEntity entity = menuRepository.findById(id).get();
        entity.setIsHidden(dto.getIsHidden());
        entity.setMenuCode(dto.getMenuCode());
        entity.setMenuIcon(dto.getMenuIcon());
        entity.setMenuTitle(dto.getMenuTitle());
        entity.setMenuUrl(dto.getMenuUrl());
        entity.setParentId(dto.getParentId());
        menuRepository.save(entity);
        buttonRepository.deleteByMenuId(id);
        List<String> buttonList = dto.getButtonList();
        if (!buttonList.isEmpty()) {
            buttonList.forEach(s -> {
                ButtonEntity buttonEntity = new ButtonEntity();
                String buttonCode = MENU_BUTTON_MAP.get(s);
                buttonEntity.setButtonCode(buttonCode);
                buttonEntity.setButtonName(s);
                buttonEntity.setMenuId(id);
                buttonRepository.save(buttonEntity);
            });
        }
    }

    @Override
    public List<MenuEntity> getList(MenuDto dto) {
        MenuEntity entity = new MenuEntity();
        entity.setMenuTitle(dto.getMenuTitle());
        entity.setParentId(dto.getParentId());
        Example<MenuEntity> example = Example.of(entity);
        List<MenuEntity> result = menuRepository.findAll(example);
        return result;
    }

    @Override
    public PageInfo<MenuDto> getPage(MenuDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<MenuDto> resultList = menuMapper.getList(dto);
        PageInfo<MenuDto> page = new PageInfo<>(resultList);
        return page;
    }

    @Override
    public List<TreeDto> getMenuTree() {
        return menuMapper.getMenuTree();
    }

    @Override
    public List<MenuDto> getListById(String menuId) {
        return menuMapper.getListById(menuId);
    }

    @Override
    public MenuDto findById(String id) {
        MenuEntity entity = menuRepository.findById(id).get();
        MenuDto result = new MenuDto();
        BeanUtils.copyProperties(entity, result);
        List<ButtonEntity> buttonEntities = buttonRepository.findByMenuId(id);
        result.setButtonList(buttonEntities.stream().map(s -> s.getButtonName()).collect(Collectors.toList()));
        return result;
    }



    @Override
    public void deleteMenu(String[] ids) {
        for (String id : ids) {
            menuRepository.deleteById(id);
        }
    }

    @Override
    public void updateIsHidden(MenuDto dto) {
        MenuEntity entity = menuRepository.findById(dto.getMenuId()).get();
        entity.setIsHidden(StringUtils.isBlank(dto.getIsHidden())?MenuEntity.IS_HIDDEN:dto.getIsHidden());
        menuRepository.save(entity);
    }
}
