package com.mall.malladmin.security;

import com.mall.malladmin.dto.system.AdminDto;
import com.mall.malladmin.enumUtil.AdminRoleEnum;
import com.mall.malladmin.mapper.system.AdminMapper;
import com.mall.malladmin.service.system.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "adminService")
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl user = new UserDetailsImpl();
        AdminDto dto  = adminService.findByLoginId(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密(SpringSecurity默认有加密)
        String encodedPassword = passwordEncoder.encode(dto.getPassword().trim());
        user.setPassword(encodedPassword);
        user.setUsername(username);
        user.setUserId(dto.getUserId());
        user.setIcon(dto.getPicUrl());
        List<String> permissionCodeList = new ArrayList<>();
        //设置用户角色
        permissionCodeList.add(AdminRoleEnum.getValue(dto.getRole()));
        //获取用户权限-按钮
        List<String> buttonCodeList = adminMapper.getButtonCodeAuthority(dto);
        permissionCodeList.addAll(buttonCodeList);
        //获取用户权限-按钮
        List<String> menuCodeList = adminMapper.getMenuCodeListAuthority(dto);
        user.setMenuList(menuCodeList);
        user.setPermissionCodeList(permissionCodeList);
        return user;
    }
}
