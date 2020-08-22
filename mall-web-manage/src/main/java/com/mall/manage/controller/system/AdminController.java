package com.mall.manage.controller.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.controller.system.utils.AdminUtil;
import com.mall.manage.model.param.system.admin.AdminCreateParam;
import com.mall.manage.model.param.system.admin.AdminDeleteParam;
import com.mall.manage.model.param.system.admin.AdminUpdateParam;
import com.mall.manage.model.param.system.admin.AdminUsableUpdateParam;
import com.mall.manage.model.param.system.menu.ButtonAuthConfirmParam;
import com.mall.manage.model.param.system.menu.MenuAuthConfirmParam;
import com.mall.manage.model.vo.system.AdminDetailVO;
import com.mall.manage.model.vo.system.AdminPageVO;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.system.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Api(value = "用户controller", tags = {"用户操作接口"})
@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AdminController extends GenericController {

    @Resource(name = "adminService")
    private AdminService adminService;

    @ApiOperation("分页查询")
    @GetMapping(value = "/page")
    public RestResult<RestPage<AdminPageVO>> getPage(@ApiParam @RequestParam Integer pageNum,
                                                        @ApiParam @RequestParam Integer pageSize) {
        Page<AdminEntity> entityPage = adminService.getPage(pageNum, pageSize);
        return RestResult.success(AdminUtil.buildPageVO(entityPage));
    }

    @ApiOperation("账号详情")
    @GetMapping(value = "/detail/{id}")
    public RestResult<AdminDetailVO> getAdminDetail(@PathVariable String id) {
        AdminEntity entity = adminService.getById(id);
        return RestResult.success(AdminUtil.buildDetailVO(entity));
    }

    @ApiOperation("新建管理员信息")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PutMapping(value = "/create")
    public RestResult<Boolean> create(@Validated @RequestBody AdminCreateParam param) {
        AdminEntity adminEntity = adminService.findByLoginCode(param.getLoginCode());
        if (Objects.nonNull(adminEntity)) {
            throw new BusinessException("登录账号已存在，不能重复");
        }
        AdminEntity createEntity = AdminUtil.buildCreateEntity(param);
        Boolean result = adminService.save(createEntity);
        return RestResult.success(result);
    }

    @ApiOperation("修改管理员信息")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update")
    public RestResult<Boolean> update(@Validated @RequestBody AdminUpdateParam param) {
        AdminEntity createEntity = AdminUtil.buildUpdateEntity(param);
        boolean result = adminService.saveOrUpdate(createEntity);
        return RestResult.success(result);
    }

    @ApiOperation("菜单授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/menu-auth/confirm")
    public RestResult<Boolean> menuAuthorityConfirm(@Validated @RequestBody MenuAuthConfirmParam param) {
        Boolean result = adminService.menuAuthority(param);
        return RestResult.success(result);
    }

    @ApiOperation("根据用户ID获取已授权菜单列表")
    @GetMapping(value = "/menu-auth/get/{id}")
    public RestResult<List<Long>> getAdminMenuAuthority(@PathVariable String id) {
        List<Long> menuList = adminService.getAdminMenuAuthority(id);
        return RestResult.success(menuList);
    }

    @ApiOperation("按钮授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/button-auth/confirm")
    public RestResult<Boolean> buttonAuthorityConfirm(@Validated @RequestBody ButtonAuthConfirmParam param) {
        Boolean result = adminService.buttonAuthority(param);
        return RestResult.success(result);
    }

    @ApiOperation("删除（逻辑删除）")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:DELETE') or hasRole('ADMIN')")
    @DeleteMapping(value = "/delete")
    public RestResult<Boolean> delete(@Validated @RequestBody AdminDeleteParam param) {
        boolean result = adminService.removeByIds(param.getIds());
        return RestResult.success(result);
    }

    @ApiOperation("是否可用")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/usable-update")
    public RestResult<Boolean> updateUsable(@Validated @RequestBody AdminUsableUpdateParam param) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsabled(param.getUsabled());
        boolean result = adminService.update(adminEntity, Wrappers.<AdminEntity>lambdaQuery().eq(AdminEntity::getId, param.getId()));
        return RestResult.success(result);
    }

    // todo  done
    @ApiOperation("获取信息")
    @GetMapping(value = "/getAdminInfo.do")
    protected RestResult getAdminInfo() {
        UserDetailsImpl result = adminService.adminLogin(this.getUserDetails().getUsername());
        return RestResult.success(result);
    }

}
