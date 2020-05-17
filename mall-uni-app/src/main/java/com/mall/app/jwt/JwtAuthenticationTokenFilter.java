package com.mall.app.jwt;

import com.alibaba.fastjson.JSONObject;
import com.mall.common.model.vo.RestResult;
import com.sun.istack.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @description: JWT登录授权过滤器
 */

@CrossOrigin
@Component
public class JwtAuthenticationTokenFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = response.getWriter();

        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isBlank(authHeader)) {
            writer.write(JSONObject.toJSONString(RestResult.unauthorized("用户未登录")));
        }
        String username = JwtTokenUtil.parseToken(authHeader);
        if (StringUtils.isBlank(username)) {
            writer.write(JSONObject.toJSONString(RestResult.unauthorized("用户未登录")));
        }
        if (JwtTokenUtil.isExpiration(authHeader)) {
            writer.write(JSONObject.toJSONString(RestResult.unauthorized("登录过期")));
        }
        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }



}