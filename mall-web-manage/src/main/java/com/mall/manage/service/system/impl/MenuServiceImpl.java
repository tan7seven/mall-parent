package com.mall.manage.service.system.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.enums.ButtonEnum;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.ButtonDTO;
import com.mall.dao.dto.system.MenuAuthorityDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.ButtonEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.dao.mapper.system.ButtonMapper;
import com.mall.dao.mapper.system.MenuMapper;
import com.mall.dao.repository.system.*;
import com.mall.manage.service.system.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "menuService")
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ButtonRepository buttonRepository;

    @Autowired
    private ButtonMapper buttonMapper;

    @Autowired
    private ButtonAuthorityRepository buttonAuthorityRepository;

    @Autowired
    private MenuAuthorityRepository menuAuthorityRepository;

//    @Autowired
//    private RedisUtil redisUtil;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public MenuEntity add(MenuDTO dto) {
        MenuEntity entity = new MenuEntity();
        BeanUtils.copyProperties(dto, entity);
        MenuEntity resultEntity = menuRepository.save(entity);
        List<String> buttonList = dto.getButtonList();
        if (!buttonList.isEmpty()) {
            buttonList.forEach(s -> {
                ButtonEntity buttonEntity = new ButtonEntity();
                String buttonCode = ButtonEnum.getKey(s);
                buttonEntity.setButtonCode(buttonCode);
                buttonEntity.setButtonName(s);
                buttonEntity.setMenuId(resultEntity.getMenuId());
                buttonRepository.save(buttonEntity);
            });
        }
        /*try {
            String[] userNameArray = adminRepository.findAll().stream().map(s -> s.getLoginCode()).toArray(String[]::new);
            redisUtil.del(userNameArray);
        }catch (Exception e){
            return resultEntity;
        }*/
        return resultEntity;
    }

    @Override
    public void update(MenuDTO dto, String id) {
        MenuEntity entity = menuRepository.findById(id).get();
        entity.setIsHidden(dto.getIsHidden());
        entity.setMenuCode(dto.getMenuCode());
        entity.setMenuIcon(dto.getMenuIcon());
        entity.setMenuTitle(dto.getMenuTitle());
        entity.setMenuUrl(dto.getMenuUrl());
        entity.setParentId(dto.getParentId());
        menuRepository.save(entity);
        this.checkButton(dto, id);
        /*try {
            String[] userNameArray = adminRepository.findAll().stream().map(s -> s.getLoginCode()).toArray(String[]::new);
            redisUtil.del(userNameArray);
        }catch (Exception e){
        }*/
    }

    @Override
    public List<MenuEntity> getList(MenuDTO dto) {
        MenuEntity entity = new MenuEntity();
        entity.setMenuTitle(dto.getMenuTitle());
        entity.setParentId(dto.getParentId());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("menuTitle" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
                ;
        Example<MenuEntity> example = Example.of(entity, matcher);
        List<MenuEntity> result = menuRepository.findAll(example);
        return result;
    }

    @Override
    public PageInfo<MenuDTO> getPage(MenuDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<MenuDTO> resultList = menuMapper.getList(dto);
        PageInfo<MenuDTO> page = new PageInfo<>(resultList);
        return page;
    }

    @Override
    public List<TreeDTO> getMenuTree(MenuAuthorityDTO dto) {
        return menuMapper.getMenuTree(dto);
    }

    @Override
    public List<MenuDTO> getListById(String menuId) {
        return menuMapper.getListById(menuId);
    }

    @Override
    public MenuDTO findById(String id) {
        MenuEntity entity = menuRepository.findById(id).get();
        MenuDTO result = new MenuDTO();
        BeanUtils.copyProperties(entity, result);
        List<ButtonEntity> buttonEntities = buttonRepository.findByMenuId(id);
        result.setButtonList(buttonEntities.stream().map(s -> s.getButtonName()).collect(Collectors.toList()));
        return result;
    }



    @Override
    public void deleteMenu(List<String> ids) {
        for (String id : ids) {
            menuRepository.deleteById(id);
            List<MenuEntity> menuList = menuRepository.findByParentId(id);
            menuList.forEach(s -> {
                this.deleteMenu(Arrays.asList(s.getMenuId()));
            });
            menuAuthorityRepository.deleteByMenuId(id);
            List<ButtonEntity> buttonEntities = buttonRepository.findByMenuId(id);
            buttonEntities.forEach(s ->this.deleteButton(s));
        }
        /*try {
            String[] userNameArray = adminRepository.findAll().stream().map(s -> s.getLoginCode()).toArray(String[]::new);
            redisUtil.del(userNameArray);
        }catch (Exception e){
        }*/
    }

    @Override
    public void updateIsHidden(MenuDTO dto) {
        MenuEntity entity = menuRepository.findById(dto.getMenuId()).get();
        entity.setIsHidden(StringUtils.isBlank(dto.getIsHidden())?MenuEntity.IS_HIDDEN:dto.getIsHidden());
        menuRepository.save(entity);
    }

    @Override
    public MenuDTO getButtonList(MenuDTO dto) {
        List<ButtonEntity> buttonEntities = buttonRepository.findByMenuId(dto.getMenuId());
        List<ButtonDTO> buttonDtos = buttonMapper.getListAuthority(dto);
        MenuDTO result = new MenuDTO();
        result.setButtonListEntity(buttonEntities);
        result.setButtonListAuthority(buttonDtos.stream().map(s -> s.getButtonName()).collect(Collectors.toList()));
        return result;
    }

    /**
     * 修改菜单信息时
     * 1、验证哪些按钮是新增
     * 2、验证哪些按钮被删除
     */
    private void checkButton(MenuDTO dto, String id){
        List<ButtonEntity> buttonEntityList = buttonRepository.findByMenuId(id);
        List<String> buttonList = dto.getButtonList();
        //1、验证哪些按钮是新增
        buttonList.forEach(s -> {
            List<String> buttonNameList = buttonEntityList.stream().map(m -> m.getButtonName()).collect(Collectors.toList());
            if (buttonNameList.indexOf(s) == -1) {
                ButtonEntity buttonEntity = new ButtonEntity();
                buttonEntity.setButtonName(s);
                buttonEntity.setButtonCode(ButtonEnum.getKey(s));
                buttonEntity.setMenuId(id);
                buttonRepository.save(buttonEntity);
            }
        });
        //2、验证哪些按钮被删除
        buttonEntityList.forEach(s -> {
            if (buttonList.indexOf(s.getButtonName()) == -1) {
                this.deleteButton(s);
            }
        });
    }

    /**
     * 删除按钮
     * 对应删除按钮的授权信息
     * @param entity
     */
    private void deleteButton(ButtonEntity entity){
        buttonRepository.delete(entity);
        buttonAuthorityRepository.deleteByButtonId(entity.getButtonId());
    }
}
