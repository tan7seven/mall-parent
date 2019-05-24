package com.mall.malladmin.security;

import com.mall.malladmin.enumUtil.AdminRoleEnum;
import com.mall.malladmin.service.AdminService;
import com.mall.malladmin.vo.AdminVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Resource(name = "userService")
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl user = new UserDetailsImpl();
        System.out.println("登录的用户名"+username);
        AdminVo vo  = adminService.findByLoginId(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密(SpringSecurity默认有加密)
        String encodedPassword = passwordEncoder.encode(vo.getPassword().trim());
        user.setPassword(encodedPassword);
        user.setUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(AdminRoleEnum.getValue(vo.getRole()));
        grantedAuthorities.add(grantedAuthority);
        user.setGrantedAuthoritys(grantedAuthorities);
        return user;
    }
}
