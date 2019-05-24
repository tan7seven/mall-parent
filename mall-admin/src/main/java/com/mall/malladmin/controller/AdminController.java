package com.mall.malladmin.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.security.UserDetailsImpl;
import com.mall.malladmin.service.AdminService;
import com.mall.malladmin.vo.AdminVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends GenericController {

    @Resource(name = "userService")
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/getAdminInfo.do")
    protected AdminVo admin(){
        UserDetailsImpl userDetail = super.getUserDetails();
        AdminVo vo = adminService.findByLoginId(userDetail.getUsername());
        log.info("根据用户ID获取到的用户信息={}", JSONObject.toJSONString(vo));
        return vo;
    }

}
