package com.mall.manage.controller.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.ButtonAuthorityEntity;
import com.mall.dao.entity.system.ButtonEntity;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.controller.system.utils.MenuUtil;
import com.mall.manage.model.param.system.menu.MenuCreateParam;
import com.mall.manage.model.param.system.menu.MenuDeleteParam;
import com.mall.manage.model.param.system.menu.MenuHiddenUpdateParam;
import com.mall.manage.model.param.system.menu.MenuUpdateParam;
import com.mall.manage.model.vo.system.ButtonListVO;
import com.mall.manage.model.vo.system.MenuDetailVO;
import com.mall.manage.model.vo.system.MenuListVO;
import com.mall.manage.service.system.ButtonAuthorityService;
import com.mall.manage.service.system.ButtonService;
import com.mall.manage.service.system.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 菜单
 */
@Api(value = "菜单", tags = "菜单")
@Slf4j
@RestController
@RequestMapping(value = "/menu")
public class MenuController extends GenericController {

    @Resource(name = "menuService")
    private MenuService menuService;
    @Autowired
    private ButtonService buttonService;
    @Autowired
    private ButtonAuthorityService buttonAuthorityService;

    @ApiOperation("根据上级ID获取下级列表")
    @GetMapping(value = "/sub-list/{parentId}")
    public RestResult<List<MenuListVO>> getSubListByParentId(@PathVariable Long parentId) {
        List<MenuEntity> menuEntityList = menuService.list(Wrappers.<MenuEntity>lambdaQuery().eq(MenuEntity::getParentId, parentId));
        List<MenuListVO> result = MenuUtil.getVOListEntity(menuEntityList);
        if (parentId.equals(0L)) {
            for (MenuListVO menuVO : result) {
                menuVO.setParentTitle("根目录");
            }
        }else {
            MenuEntity menuEntity = menuService.getById(parentId);
            for (MenuListVO menuVO : result) {
                menuVO.setParentTitle(menuEntity.getMenuTitle());
            }
        }
        return RestResult.success(result);
    }

    @ApiOperation("获取菜单树")
    @GetMapping(value = "/menu-tree/get")
    public RestResult<List<TreeDTO>> getMenuTree(@ApiParam(value = "菜单ID") @RequestParam(required = false) Long userId) {
        List<TreeDTO> result = menuService.getMenuTree(userId);
        return RestResult.success(result);
    }

    @ApiOperation("获取按钮列表")
    @GetMapping(value = "/button/list")
    public RestResult<ButtonListVO> getButtonList(@ApiParam(value = "菜单ID") @RequestParam Long menuId, @ApiParam(value = "账号ID") @RequestParam Long userId) {
        List<ButtonAuthorityEntity> authorityEntityList = buttonAuthorityService.list(Wrappers.<ButtonAuthorityEntity>lambdaQuery()
                .eq(ButtonAuthorityEntity::getMenuId, menuId)
                .eq(ButtonAuthorityEntity::getUserId, userId));
        return RestResult.success(MenuUtil.buildButtonListVO(authorityEntityList));
    }

    @ApiOperation("新建")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:CREATE') or hasRole('ADMIN')")
    @PutMapping(value = "/create")
    public RestResult<Boolean> createMenu(@Validated @RequestBody MenuCreateParam param) {
        Boolean result = menuService.addMenuAndButton(param);
        return RestResult.success(result);
    }

    @ApiOperation("菜单详情")
    @GetMapping(value = "/detail/{id}")
    public RestResult<MenuDetailVO> detail(@PathVariable String id) {
        MenuEntity menuEntity = menuService.getById(id);
        List<ButtonEntity> buttonList = buttonService.list(Wrappers.<ButtonEntity>lambdaQuery().eq(ButtonEntity::getMenuId, id));
        return RestResult.success(MenuUtil.buildMenuDetailVO(menuEntity, buttonList));
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "/update")
    public RestResult<Boolean> updateMenu(@Validated @RequestBody MenuUpdateParam param) {
        Boolean result = menuService.updateMenuAndButton(param);
        return RestResult.success(result);
    }

    @ApiOperation("删除")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:DELETE') or hasRole('ADMIN')")
    @DeleteMapping(value = "/delete")
    public RestResult<Boolean> deleteMenu(@Validated @RequestBody MenuDeleteParam param) {
        menuService.removeByIds(param.getIds());
        buttonService.remove(Wrappers.<ButtonEntity>lambdaQuery().in(ButtonEntity::getMenuId, param.getIds()));
        return RestResult.success(Boolean.TRUE);
    }

    @ApiOperation("是否隐藏")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "/hidden-update")
    public RestResult<Boolean> updateHidden(@Validated @RequestBody MenuHiddenUpdateParam param) {
        MenuEntity entity = new MenuEntity();
        entity.setHidden(param.getHidden());
        boolean result = menuService.update(entity, Wrappers.<MenuEntity>lambdaQuery().eq(MenuEntity::getId, param.getMenuId()));
        return RestResult.success(result);
    }
}
