package com.mall.manage.service.system.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mall.common.enums.AdminRoleEnum;
import com.mall.common.exception.BusinessException;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.dao.entity.system.ButtonAuthorityEntity;
import com.mall.dao.entity.system.MenuAuthorityEntity;
import com.mall.dao.mapper.system.AdminMapper;
import com.mall.manage.model.param.system.menu.ButtonAuthConfirmParam;
import com.mall.manage.model.param.system.menu.MenuAuthConfirmParam;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.system.AdminService;
import com.mall.manage.service.system.ButtonAuthorityService;
import com.mall.manage.service.system.MenuAuthorityService;
import com.mall.manage.service.system.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {

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
        AdminEntity entity = this.findByLoginCode(username);
        if (null == entity) {
            throw new BusinessException("账号错误");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密(SpringSecurity默认有加密)
        String encodedPassword = passwordEncoder.encode(entity.getPassword().trim());
        user.setPassword(encodedPassword);
        user.setUsername(username);
        user.setUserId(entity.getId());
        user.setIcon(entity.getPicUrl());
        List<String> buttonList = new ArrayList<>();
        //获取用户角色
        user.setRole(AdminRoleEnum.getValue(entity.getRole()));
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
    public Boolean menuAuthority(MenuAuthConfirmParam param) {
        AdminEntity adminEntity = this.getById(param.getUserId());
        menuAuthorityService.remove(Wrappers.<MenuAuthorityEntity>lambdaQuery().eq(MenuAuthorityEntity::getUserId, adminEntity.getId()));
        if (CollectionUtils.isEmpty(param.getMenuList())) {
            return Boolean.TRUE;
        }
        List<MenuAuthorityEntity> menuAuthList = Lists.newArrayList();
        param.getMenuList().forEach(s -> {
            MenuAuthorityEntity authorityEntity = new MenuAuthorityEntity();
            authorityEntity.setMenuId(s);
            authorityEntity.setUserId(adminEntity.getId());
            menuAuthList.add(authorityEntity);
        });
        boolean result = menuAuthorityService.saveBatch(menuAuthList);
        return result;
    }

    @Override
    public Boolean buttonAuthority(ButtonAuthConfirmParam param) {
        buttonAuthorityService.remove(Wrappers.<ButtonAuthorityEntity>lambdaQuery()
                .eq(ButtonAuthorityEntity::getUserId, param.getUserId())
                .eq(ButtonAuthorityEntity::getMenuId, param.getMenuId()));
        if (CollectionUtils.isEmpty(param.getButtonList())) {
            return Boolean.TRUE;
        }
        List<ButtonAuthorityEntity> buttonList = Lists.newArrayList();
        for (String buttonCode : param.getButtonList()) {
            ButtonAuthorityEntity entity = new ButtonAuthorityEntity();
            entity.setButtonCode(buttonCode);
            entity.setMenuId(param.getMenuId());
            entity.setUserId(param.getUserId());
            buttonList.add(entity);
        }
        boolean result = buttonAuthorityService.saveBatch(buttonList);
        return result;
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
