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
        UserDetailsImpl user = adminService.adminLogin(username);
        return user;
    }
}
