package com.mall.malladmin.controller.system;

import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.system.AdminDTO;
import com.mall.malladmin.entity.system.AdminEntity;
import com.mall.malladmin.security.UserDetailsImpl;
import com.mall.malladmin.service.system.AdminService;
import com.mall.malladmin.util.ResultPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value="用户controller",tags={"用户操作接口"})
@Slf4j
@RestController
@RequestMapping(value = "/adminController")
public class AdminController extends GenericController {

    @Resource(name = "adminService")
    private AdminService adminService;

    @ApiOperation("分页查询")
    @PostMapping(value = "getPage.do")
    protected CommonResultDTO getPage(AdminDTO dto){
        Page<AdminEntity> result = adminService.getPage(dto);
        ResultPage resultPage = new ResultPage();
        resultPage.setList(result.getContent());
        resultPage.setTotal(result.getTotalElements());
        return new CommonResultDTO().success(resultPage);
    }

    @ApiOperation("获取信息")
    @GetMapping(value = "/getAdminInfo.do")
    protected CommonResultDTO getAdminInfo(){
        UserDetailsImpl result = adminService.adminLogin(this.getUserDetails().getUsername());
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("获取信息")
    @GetMapping(value = "/getAdminInfo.do/{id}")
    protected CommonResultDTO getAdminInfo(@PathVariable String id){
        AdminEntity result = adminService.findById(id).get();
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("新建管理员信息")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "createAdmin.do")
    protected CommonResultDTO createAdmin(@RequestBody AdminDTO dto){
        adminService.add(dto);
        return new CommonResultDTO().success();
    }

    @ApiOperation("修改管理员信息")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateAdmin.do/{id}")
    protected CommonResultDTO updateAdmin(@RequestBody AdminDTO dto, @PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDTO().validateFailed("ID为空！");
        }
        adminService.update(dto, id);
        return new CommonResultDTO().success();
    }

    @ApiOperation("删除（逻辑删除）")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteAdmin.do")
    protected CommonResultDTO deleteAdmin(List<String> ids){
        if(null == ids || ids.isEmpty()){
            return new CommonResultDTO().validateFailed("id为空！");
        }
        adminService.deleteAdmin(ids);
        return new CommonResultDTO().success();
    }

    @ApiOperation("是否可用")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "updateIsUsable.do")
    protected CommonResultDTO updateIsUsable(@RequestBody AdminDTO dto){
        if(StringUtils.isBlank(dto.getUserId())){
            return new CommonResultDTO().validateFailed("用户编码为空！");
        }
        adminService.updateIsUsable(dto);
        return new CommonResultDTO().success();
    }

    @ApiOperation("菜单授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "menuAuthorityConfirm.do")
    protected CommonResultDTO menuAuthorityConfirm(@RequestBody AdminDTO dto){
        if (StringUtils.isBlank(dto.getUserId())) {
            return new CommonResultDTO().forbidden("用户ID为空！");
        }
        adminService.menuAuthority(dto);
        return new CommonResultDTO().success();
    }

    @ApiOperation("按钮授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "buttonAuthorityConfirm.do")
    protected CommonResultDTO buttonAuthorityConfirm(@RequestBody AdminDTO dto){
        if (StringUtils.isBlank(dto.getUserId()) || StringUtils.isBlank(dto.getMenuId())) {
            return new CommonResultDTO().forbidden("用户ID活菜单ID为空！");
        }
        adminService.buttonAuthority(dto);
        return new CommonResultDTO().success();
    }

    @ApiOperation("根据用户ID获取已授权菜单列表")
    @GetMapping(value = "getAdminMenuAuthority.do/{id}")
    protected CommonResultDTO getAdminMenuAuthority(@PathVariable String id){
        List<String> menuList =  adminService.getAdminMenuAuthority(id);
        return new CommonResultDTO().success(menuList);
    }
}
