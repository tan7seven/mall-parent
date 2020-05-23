package com.mall.app.jwt;

import com.alibaba.fastjson.JSON;
import com.mall.common.enums.ResultStatus;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestResult;
import com.mall.common.model.vo.ResultVO;
import com.sun.istack.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: JWT登录授权过滤器
 */

@CrossOrigin
@Component
public class JwtAuthenticationTokenFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        /** 跨域验证不处理 */
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        /*String authHeader = request.getHeader("Authorization");
        if (StringUtils.isBlank(authHeader)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().write(JSON.toJSONString(RestResult.failed(ResultStatus.USER_NEED_AUTHORITIES)));
            return false;
        }
        String username = JwtTokenUtil.parseTokenGetUserId(authHeader);
        if (StringUtils.isBlank(username)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().write(JSON.toJSONString(RestResult.failed(ResultStatus.USER_NEED_AUTHORITIES)));
            return false;
        }
        request.setAttribute("username", username);*/
        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
    }



}