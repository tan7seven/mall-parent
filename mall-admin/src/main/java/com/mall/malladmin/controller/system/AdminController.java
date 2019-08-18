package com.mall.malladmin.controller.system;

import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.system.AdminDto;
import com.mall.malladmin.entity.system.AdminEntity;
import com.mall.malladmin.service.system.AdminService;
import com.mall.malladmin.util.ResultPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/adminController")
public class AdminController extends GenericController {

    @Resource(name = "userService")
    private AdminService adminService;

    /**
     *分页查询
     * @param dto
     * @return
     */
    @PostMapping(value = "getPage.do")
    protected Object getPage(AdminDto dto){
        Page<AdminEntity> result = adminService.getPage(dto);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultDto().success(resultPage);
    }
}
