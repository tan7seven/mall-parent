package com.mall.malladmin.controller.system;

import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.system.AdminDto;
import com.mall.malladmin.entity.system.AdminEntity;
import com.mall.malladmin.security.UserDetailsImpl;
import com.mall.malladmin.service.system.AdminService;
import com.mall.malladmin.util.ResultPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/adminController")
public class AdminController extends GenericController {

    @Resource(name = "adminService")
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
    /**
     * 获取信息
     * @return
     */
    @GetMapping(value = "/getAdminInfo.do")
    protected  Object getAdminInfo(){
        UserDetailsImpl result = adminService.adminLogin(this.getUserDetails().getUsername());
        return new CommonResultDto().success(result);
    }
    /**
     * 获取信息
     * @param id
     * @return
     */
    @GetMapping(value = "/getAdminInfo.do/{id}")
    protected  Object getAdminInfo(@PathVariable String id){
        AdminEntity result = adminService.findById(id).get();
        return new CommonResultDto().success(result);
    }

    /**
     * 新建管理员信息
     * @param dto
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "createAdmin.do")
    protected Object createAdmin(@RequestBody AdminDto dto){
        adminService.add(dto);
        return new CommonResultDto().success();
    }

    /**
     * 修改管理员信息
     * @param dto
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateAdmin.do/{id}")
    protected Object updateAdmin(@RequestBody AdminDto dto,@PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDto().validateFailed("ID为空！");
        }
        adminService.update(dto, id);
        return new CommonResultDto().success();
    }

    /**
     * 删除（逻辑删除）
     */
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteAdmin.do")
    protected Object deleteAdmin(String...ids){
        if(null == ids || ids.length==0){
            return new CommonResultDto().validateFailed("id为空！");
        }
        adminService.deleteAdmin(ids);
        return new CommonResultDto().success();
    }

    /**
     * 是否可用
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "updateIsUsable.do")
    protected Object updateIsUsable(@RequestBody AdminDto dto){
        if(StringUtils.isBlank(dto.getUserId())){
            return new CommonResultDto().validateFailed("用户编码为空！");
        }
        adminService.updateIsUsable(dto);
        return new CommonResultDto().success();
    }

    /**
     * 菜单授权
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "menuAuthorityConfirm.do")
    protected Object menuAuthorityConfirm(@RequestBody AdminDto dto){
        if (StringUtils.isBlank(dto.getUserId())) {
            return new CommonResultDto().forbidden("用户ID为空！");
        }
        adminService.menuAuthority(dto);
        return new CommonResultDto().success();
    }

    /**
     * 按钮授权
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "buttonAuthorityConfirm.do")
    protected Object buttonAuthorityConfirm(@RequestBody AdminDto dto){
        if (StringUtils.isBlank(dto.getUserId()) || StringUtils.isBlank(dto.getMenuId())) {
            return new CommonResultDto().forbidden("用户ID活菜单ID为空！");
        }
        adminService.buttonAuthority(dto);
        return new CommonResultDto().success();
    }

    /**
     * 根据用户ID获取已授权菜单列表
     * @param id
     * @return
     */
    @GetMapping(value = "getAdminMenuAuthority.do/{id}")
    protected Object getAdminMenuAuthority(@PathVariable String id){
        List<String> menuList =  adminService.getAdminMenuAuthority(id);
        return new CommonResultDto().success(menuList);
    }
}
