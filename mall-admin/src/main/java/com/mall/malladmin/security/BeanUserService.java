package com.mall.malladmin.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BeanUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("进入BeanUserService"+s);
        BeanUser user = new BeanUser();
        user.setPassword("admin");
        user.setUsername("admin");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.setGrantedAuthoritys(grantedAuthorities);
        return user;
    }
}
