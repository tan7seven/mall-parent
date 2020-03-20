package com.mall.manage.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.system.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    protected RestResult getPage(AdminDTO dto){
        Page<AdminEntity> result = adminService.getPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("获取信息")
    @GetMapping(value = "/getAdminInfo.do")
    protected RestResult getAdminInfo(){
        UserDetailsImpl result = adminService.adminLogin(this.getUserDetails().getUsername());
        return RestResult.success(result);
    }

    @ApiOperation("获取信息")
    @GetMapping(value = "/getAdminInfo.do/{id}")
    protected RestResult getAdminInfo(@PathVariable String id){
        AdminEntity result = adminService.findById(id).get();
        return RestResult.success(result);
    }

    @ApiOperation("新建管理员信息")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "createAdmin.do")
    protected RestResult createAdmin(@RequestBody AdminDTO dto){
        adminService.add(dto);
        return RestResult.success();
    }

    @ApiOperation("修改管理员信息")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateAdmin.do/{id}")
    protected RestResult updateAdmin(@RequestBody AdminDTO dto, @PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return RestResult.validateFailed("ID为空！");
        }
        adminService.update(dto, id);
        return RestResult.success();
    }

    @ApiOperation("删除（逻辑删除）")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteAdmin.do")
    protected RestResult deleteAdmin(List<String> ids){
        if(null == ids || ids.isEmpty()){
            return RestResult.validateFailed("id为空！");
        }
        adminService.deleteAdmin(ids);
        return RestResult.success();
    }

    @ApiOperation("是否可用")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "updateIsUsable.do")
    protected RestResult updateIsUsable(@RequestBody AdminDTO dto){
        if(StringUtils.isBlank(dto.getUserId())){
            return RestResult.validateFailed("用户编码为空！");
        }
        adminService.updateIsUsable(dto);
        return RestResult.success();
    }

    @ApiOperation("菜单授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "menuAuthorityConfirm.do")
    protected RestResult menuAuthorityConfirm(@RequestBody AdminDTO dto){
        if (StringUtils.isBlank(dto.getUserId())) {
            return RestResult.forbidden("用户ID为空！");
        }
        adminService.menuAuthority(dto);
        return RestResult.success();
    }

    @ApiOperation("按钮授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "buttonAuthorityConfirm.do")
    protected RestResult buttonAuthorityConfirm(@RequestBody AdminDTO dto){
        if (StringUtils.isBlank(dto.getUserId()) || StringUtils.isBlank(dto.getMenuId())) {
            return RestResult.forbidden("用户ID活菜单ID为空！");
        }
        adminService.buttonAuthority(dto);
        return RestResult.success();
    }

    @ApiOperation("根据用户ID获取已授权菜单列表")
    @GetMapping(value = "getAdminMenuAuthority.do/{id}")
    protected RestResult getAdminMenuAuthority(@PathVariable String id){
        List<String> menuList =  adminService.getAdminMenuAuthority(id);
        return RestResult.success(menuList);
    }
}
