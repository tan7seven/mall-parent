package com.mall.manage.service.system.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.enums.AdminRoleEnum;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.dao.entity.system.ButtonAuthorityEntity;
import com.mall.dao.entity.system.MenuAuthorityEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.dao.mapper.system.AdminMapper;
import com.mall.dao.mapper.system.ButtonMapper;
import com.mall.manage.model.param.system.MenuAuthConfirmParam;
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
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuAuthorityService menuAuthorityService;

    @Autowired
    private ButtonAuthorityService buttonAuthorityService;

    @Override
    public AdminEntity findByLoginCode(String loginCode) {
        return this.baseMapper.selectOne(Wrappers.<AdminEntity>lambdaQuery().eq(AdminEntity::getLoginCode, loginCode));
    }

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
//        List<String> buttonCodeList = this.baseMapper.getButtonCodeAuthority(dto);
//        buttonList.addAll(buttonCodeList);
//        user.setButtonList(buttonList);
        //获取用户权限-菜单
//        List<String> menuCodeList = this.baseMapper.getMenuCodeListAuthority(dto);
//        user.setMenuList(menuCodeList);
        return user;
    }

    @Override
    public Page<AdminEntity> getPage(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        Page<AdminEntity> result = (Page<AdminEntity>) this.page(page);
        return result;
    }

    @Override
    public AdminDTO findByLoginId(String loginCode) {
        return this.baseMapper.findByLoginId(loginCode);
    }

    @Override
    public void deleteAdmin(List<String> ids) {
        for (String id : ids) {
            AdminEntity entity = this.getById(id);
            entity.setDeleted(Boolean.TRUE);
            this.save(entity);
        }
    }

    @Override
    public void updateIsUsable(AdminDTO dto) {
        AdminEntity entity = this.getById(dto.getUserId());
        entity.setUsabled(null == dto.getIsUsable()?Boolean.TRUE:dto.getIsUsable());
        this.save(entity);
    }

    @Override
    public Boolean menuAuthority(MenuAuthConfirmParam param) {
        AdminEntity adminEntity = this.findByLoginCode(param.getLoginCode());
        menuAuthorityService.remove(Wrappers.<MenuAuthorityEntity>lambdaQuery().eq(MenuAuthorityEntity::getUserId, adminEntity.getId()));
        if (CollectionUtils.isEmpty(param.getMenuList())) {
            return Boolean.TRUE;
        }
        List<MenuAuthorityEntity> menuAnthList = Lists.newArrayList();
        param.getMenuList().forEach(s -> {
            MenuAuthorityEntity authorityEntity = new MenuAuthorityEntity();
            authorityEntity.setMenuId(s);
            authorityEntity.setUserId(adminEntity.getId());
            menuAnthList.add(authorityEntity);
        });
        boolean result = menuAuthorityService.saveBatch(menuAnthList);
        return result;
    }

    @Override
    public void buttonAuthority(AdminDTO dto) {
        dto.getButtonList().forEach(s -> {
            ButtonAuthorityEntity authorityEntity = new ButtonAuthorityEntity();
            buttonAuthorityService.save(authorityEntity);
        });
    }

    @Override
    public List<Long> getAdminMenuAuthority(String userId) {
        List<MenuAuthorityEntity> entityList = menuAuthorityService.list(Wrappers.<MenuAuthorityEntity>lambdaQuery().eq(MenuAuthorityEntity::getUserId, userId));
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        return entityList.stream().map(s -> s.getMenuId()).collect(Collectors.toList());
    }

}
