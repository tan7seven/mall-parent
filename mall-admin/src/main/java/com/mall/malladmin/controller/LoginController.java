package com.mall.malladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.malladmin.entity.UserEntity;
import com.mall.malladmin.service.UserService;
import com.mall.malladmin.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Controller
public class LoginController {

    @Resource(name = "userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/admin.do")
    protected String admin(UserVo dto){
        Optional<UserEntity> entitys = userService.findById(dto.getUserId());
        UserEntity entity = entitys.get();
        log.info("根据用户ID获取到的用户信息={}", JSONObject.toJSONString(entity));
        return "success";
    }

    @RequestMapping("/login.do")
    public String index() {
        System.out.println("login.do");
        return "login";
    }

}
