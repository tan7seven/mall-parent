package com.mall.malladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.malladmin.dto.UserDto;
import com.mall.malladmin.entity.UserEntity;
import com.mall.malladmin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/admin.do")
    protected String admin(UserDto dto){
        Optional<UserEntity> entitys = userService.findById(dto.getUserId());
        UserEntity entity = entitys.get();
        log.info("根据用户ID获取到的用户信息={}", JSONObject.toJSONString(entity));
        return "success";
    }
    @ResponseBody
    @RequestMapping("/login.do")
    public String index() {

        System.out.println("login.do");
        return "index";
    }

    @RequestMapping(value = "/toLogin.do")
    protected String login(){
        System.out.println("toLogin.do");
        return "login";
    }
}
