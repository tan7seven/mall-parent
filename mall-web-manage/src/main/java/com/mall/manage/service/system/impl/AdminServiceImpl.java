package com.mall.manage.service.system.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.enums.AdminRoleEnum;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.dao.entity.system.ButtonAuthorityEntity;
import com.mall.dao.entity.system.MenuAuthorityEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.dao.mapper.system.AdminMapper;
import com.mall.dao.mapper.system.ButtonMapper;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.system.AdminService;
import com.mall.manage.service.system.ButtonAuthorityService;
import com.mall.manage.service.system.MenuAuthorityService;
import com.mall.manage.service.system.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ButtonMapper buttonMapper;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuAuthorityService menuAuthorityService;

    @Autowired
    private ButtonAuthorityService buttonAuthorityService;

    @Override
    public UserDetailsImpl adminLogin(String username) {
        UserDetailsImpl user = new UserDetailsImpl();
        AdminDTO dto  = this.findByLoginId(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密(SpringSecurity默认有加密)
        String encodedPassword = passwordEncoder.encode(dto.getPassword().trim());
        user.setPassword(encodedPassword);
        user.setUsername(username);
        user.setUserId(dto.getUserId());
        user.setIcon(dto.getPicUrl());
        List<String> buttonList = new ArrayList<>();
        //获取用户角色
        user.setRole(AdminRoleEnum.getValue(dto.getRole()));
        //获取用户权限-按钮
        List<String> buttonCodeList = adminMapper.getButtonCodeAuthority(dto);
        buttonList.addAll(buttonCodeList);
        user.setButtonList(buttonList);
        //获取用户权限-菜单
        List<String> menuCodeList = adminMapper.getMenuCodeListAuthority(dto);
        user.setMenuList(menuCodeList);
        return user;
    }

    @Override
    public AdminEntity add(AdminDTO dto) {
        AdminEntity entity = new AdminEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreateTime(new Date());
        entity.setIsUsable(null == entity.getIsUsable()?entity.getIsUsable(): Boolean.FALSE);
        entity.setModifyTime(new Date());
        entity.setRole(AdminEntity.ROLE_USER);
        entity.setPassword(AdminEntity.DEFAULT_PASSWORD);
        entity.setIsDelete(Boolean.FALSE);
        this.save(entity);
        return entity;
    }

    @Override
    public void update(AdminDTO dto, String id) {
        AdminEntity entity = this.getById(id);
        entity.setLoginCode(dto.getLoginCode());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setPicUrl(dto.getPicUrl());
        entity.setIsUsable(dto.getIsUsable());
        entity.setModifyTime(new Date());
        this.save(entity);
    }

    @Override
    public List<AdminEntity> getList(AdminDTO dto) {
        List<AdminEntity>entities = this.list();
        return entities;
    }

    @Override
    public Page<AdminEntity> getPage(AdminDTO dto) {
        Page page = new Page(dto.getPageNum()-1, dto.getPageSize());
        AdminEntity entity = new AdminEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setIsDelete(Boolean.FALSE);
        Page<AdminEntity> result = (Page<AdminEntity>) this.page(page);
        return result;
    }

    @Override
    public AdminDTO findByLoginId(String loginCode) {
        return adminMapper.findByLoginId(loginCode);
    }

    @Override
    public void deleteAdmin(List<String> ids) {
        for (String id : ids) {
            AdminEntity entity = this.getById(id);
            entity.setIsDelete(Boolean.TRUE);
            this.save(entity);
        }
    }

    @Override
    public void updateIsUsable(AdminDTO dto) {
        AdminEntity entity = this.getById(dto.getUserId());
        entity.setIsUsable(null == dto.getIsUsable()?Boolean.TRUE:dto.getIsUsable());
        this.save(entity);
    }

    @Override
    public void menuAuthority(AdminDTO dto) {
        menuAuthorityService.remove(Wrappers.<MenuAuthorityEntity>lambdaQuery().eq(MenuAuthorityEntity::getMenuId, dto.getUserId()));
        if (dto.getMenuList().isEmpty()) {
            return;
        }
        dto.getMenuList().forEach(s -> {
            MenuEntity menuEntity = menuService.getById(s);
            if(!"0".equals(menuEntity.getParentId())){
                MenuAuthorityEntity authorityEntity = new MenuAuthorityEntity();
                authorityEntity.setMenuId(s);
                authorityEntity.setUserId(dto.getUserId());
                menuAuthorityService.save(authorityEntity);
            }
        });
    }

    @Override
    public void buttonAuthority(AdminDTO dto) {
        buttonMapper.deleteByMenuIdAndUserId(dto);
        dto.getButtonList().forEach(s -> {
            ButtonAuthorityEntity authorityEntity = new ButtonAuthorityEntity();
            authorityEntity.setButtonId(s.getButtonId());
            authorityEntity.setMenuId(dto.getMenuId());
            authorityEntity.setUserId(dto.getUserId());
            buttonAuthorityService.save(authorityEntity);
        });
    }

    @Override
    public List<String> getAdminMenuAuthority(String userId) {
        return menuAuthorityService.list(Wrappers.<MenuAuthorityEntity>lambdaQuery().eq(MenuAuthorityEntity::getUserId, userId)).stream().map(s -> s.getMenuId()).collect(Collectors.toList());
    }

}
