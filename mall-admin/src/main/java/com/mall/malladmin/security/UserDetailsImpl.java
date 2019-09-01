package com.mall.malladmin.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private String userId;
    private String password;
    private String username;
    /**
     * 头像
     */
    private String icon;
    private List<String> permissionCodeList;
    private List<String> menuList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionCodeList.stream().map(
                s -> new SimpleGrantedAuthority(s)
        ).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
