package com.mall.manage.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String password;
    private String username;
    /**
     * 用户角色
     */
    private String role;
    /**
     * 头像
     */
    private String icon;
    private List<String> buttonList;
    private List<String> menuList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorityList = new ArrayList<>();
        //按钮权限列表
/*        authorityList.addAll(this.buttonList.stream().map(
                s -> new SimpleGrantedAuthority(s)
        ).collect(Collectors.toList()));*/
        //用户角色
        authorityList.add(new SimpleGrantedAuthority(this.role));
        return authorityList;
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
