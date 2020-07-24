package com.mall.manage.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.controller.system.utils.AdminUtil;
import com.mall.manage.model.param.system.AdminCreateParam;
import com.mall.manage.model.param.system.AdminUpdateParam;
import com.mall.manage.model.param.system.MenuAuthConfirmParam;
import com.mall.manage.model.vo.system.AdminDetailVO;
import com.mall.manage.model.vo.system.AdminPageVO;
import com.mall.manage.security.UserDetailsImpl;
import com.mall.manage.service.system.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    protected RestResult<RestPage<AdminPageVO>> getPage(@ApiParam @RequestParam Integer pageNum,
                                                        @ApiParam @RequestParam Integer pageSize) {
        Page<AdminEntity> entityPage = adminService.getPage(pageNum, pageSize);
        return RestResult.success(AdminUtil.buildPageVO(entityPage));
    }

    @ApiOperation("账号详情")
    @GetMapping(value = "/detail/{id}")
    protected RestResult<AdminDetailVO> getAdminDetail(@PathVariable String id) {
        AdminEntity entity = adminService.getById(id);
        return RestResult.success(AdminUtil.buildDetailVO(entity));
    }

    @ApiOperation("新建管理员信息")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PutMapping(value = "/create")
    protected RestResult<Boolean> create(@Validated @RequestBody AdminCreateParam param) {
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
    protected RestResult<Boolean> update(@Validated @RequestBody AdminUpdateParam param) {
        AdminEntity createEntity = AdminUtil.buildUpdateEntity(param);
        boolean result = adminService.saveOrUpdate(createEntity);
        return RestResult.success(result);
    }

    @ApiOperation("菜单授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/menu-auth/confirm")
    protected RestResult<Boolean> menuAuthorityConfirm(@Validated @RequestBody MenuAuthConfirmParam param) {
        Boolean result = adminService.menuAuthority(param);
        return RestResult.success(result);
    }

    @ApiOperation("根据用户ID获取已授权菜单列表")
    @GetMapping(value = "/menu-auth/get/{id}")
    protected RestResult getAdminMenuAuthority(@PathVariable String id) {
        List<Long> menuList = adminService.getAdminMenuAuthority(id);
        return RestResult.success(menuList);
    }

    // todo  done
    @ApiOperation("获取信息")
    @GetMapping(value = "/getAdminInfo.do")
    protected RestResult getAdminInfo() {
        UserDetailsImpl result = adminService.adminLogin(this.getUserDetails().getUsername());
        return RestResult.success(result);
    }

    @ApiOperation("删除（逻辑删除）")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteAdmin.do")
    protected RestResult deleteAdmin(List<String> ids) {
        if (null == ids || ids.isEmpty()) {
            return RestResult.validateFailed("id为空！");
        }
        adminService.deleteAdmin(ids);
        return RestResult.success();
    }

    @ApiOperation("是否可用")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "updateIsUsable.do")
    protected RestResult updateIsUsable(@RequestBody AdminDTO dto) {
        if (StringUtils.isBlank(dto.getUserId())) {
            return RestResult.validateFailed("用户编码为空！");
        }
        adminService.updateIsUsable(dto);
        return RestResult.success();
    }



    @ApiOperation("按钮授权")
    @PreAuthorize(" hasAuthority('SYSTEM:ADMIN:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "buttonAuthorityConfirm.do")
    protected RestResult buttonAuthorityConfirm(@RequestBody AdminDTO dto) {
        adminService.buttonAuthority(dto);
        return RestResult.success();
    }


}
