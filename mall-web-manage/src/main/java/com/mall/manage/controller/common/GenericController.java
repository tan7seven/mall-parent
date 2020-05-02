package com.mall.manage.controller.common;

import com.mall.manage.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApiIgnore
@Slf4j
@Controller
public class GenericController {

    public UserDetailsImpl getUserDetails() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            if(securityContextImpl == null){
                return null;
            }
            authentication = securityContextImpl.getAuthentication();
            if (authentication == null) {
                return null;
            }
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            return (UserDetailsImpl) principal;
        }
        return null;
    }
    public HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {

        }
        return session;
    }
    public HttpServletRequest getRequest() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return req;
    }
}
