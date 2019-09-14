package com.mall.malladmin.security;

import com.mall.malladmin.service.system.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "adminService")
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl user = adminService.adminLogin(username);
        return user;
    }
}
