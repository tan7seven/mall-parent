package com.mall.app.jwt;

import com.mall.common.exception.BusinessException;
import com.sun.istack.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: JWT登录授权过滤器
 */

@CrossOrigin
@Component
public class JwtAuthenticationTokenFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isBlank(authHeader)) {
            throw new BusinessException("用户未登陆");
        }
        String username = JwtTokenUtil.parseTokenGetUserId(authHeader);
        if (StringUtils.isBlank(username)) {
            throw new BusinessException("用户未登陆");
        }
        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
    }



}