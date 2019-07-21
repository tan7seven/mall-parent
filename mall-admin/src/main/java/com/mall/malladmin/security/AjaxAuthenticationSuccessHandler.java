package com.mall.malladmin.security;

import com.alibaba.fastjson.JSON;
import com.mall.malladmin.jwt.JwtTokenUtil;
import com.mall.malladmin.dto.common.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 用户登录成功时返回给前端的数据
 */
@Slf4j
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = JwtTokenUtil.generateToken(userDetails);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        log.info("传给前段的token：{}", jwtToken);
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultDto.success("登录成功！",userDetails ,jwtToken)));
    }
}