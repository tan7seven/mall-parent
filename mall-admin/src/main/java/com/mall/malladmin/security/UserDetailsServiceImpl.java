package com.mall.malladmin.security;

import com.mall.malladmin.enumUtil.AdminRoleEnum;
import com.mall.malladmin.service.AdminService;
import com.mall.malladmin.dto.system.AdminDto;
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
        AdminDto dto  = adminService.findByLoginId(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密(SpringSecurity默认有加密)
        String encodedPassword = passwordEncoder.encode(dto.getPassword().trim());
        user.setPassword(encodedPassword);
        user.setUsername(username);
        user.setUserId(dto.getUserId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(AdminRoleEnum.getValue(dto.getRole()));
        grantedAuthorities.add(grantedAuthority);
        user.setGrantedAuthoritys(grantedAuthorities);
        return user;
    }
}
