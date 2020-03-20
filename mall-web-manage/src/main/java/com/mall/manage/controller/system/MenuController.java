package com.mall.manage.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.common.TreeDTO;
import com.mall.dao.dto.system.MenuAuthorityDTO;
import com.mall.dao.dto.system.MenuDTO;
import com.mall.dao.entity.system.MenuEntity;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.service.system.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 菜单
 */
@Api(value = "菜单", tags = "菜单")
@Slf4j
@RestController
@RequestMapping(value = "/menuController")
public class MenuController extends GenericController {

    @Resource(name = "menuService")
    private MenuService menuService;

    @ApiOperation("分页查询")
    @PostMapping(value = "getPage.do")
    protected RestResult getPage(MenuDTO dto){
        Page<MenuDTO> result = menuService.getPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("详情")
    @PostMapping(value = "getMenuListById.do")
    protected  RestResult getMenuListById(String menuId){
       List<MenuDTO> result =  menuService.getListById(menuId);
        return RestResult.success(result);
    }

    @ApiOperation("获取信息")
    @GetMapping(value = "/getMenuInfo.do/{id}")
    protected  RestResult getMenuInfo(@PathVariable String id){
        MenuDTO result = menuService.findById(id);
        return RestResult.success(result);
    }

    @ApiOperation("新建")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "createMenu.do")
    protected RestResult createMenu(@RequestBody MenuDTO dto){
        menuService.add(dto);
        return RestResult.success();
    }

    @ApiOperation("修改")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateMenu.do/{id}")
    protected RestResult updateMenu(@RequestBody MenuDTO dto, @PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return RestResult.validateFailed("ID为空！");
        }
        menuService.update(dto, id);
        return RestResult.success();
    }

    @ApiOperation("删除")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteMenu.do")
    protected RestResult deleteMenu(List<String> ids){
        if(null == ids || ids.size()==0){
            return RestResult.validateFailed("id为空！");
        }
        menuService.deleteMenu(ids);
        return RestResult.success();
    }

    @ApiOperation("是否隐藏")
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "updateIsHidden.do")
    protected RestResult updateIsHidden(@RequestBody MenuDTO dto){
        if(StringUtils.isBlank(dto.getMenuId())){
            return RestResult.validateFailed("用户编码为空！");
        }
        menuService.updateIsHidden(dto);
        return RestResult.success();
    }

    @ApiOperation("获取父级ID获取菜单列表")
    @PostMapping(value = "getMenuList.do/{id}")
    protected RestResult getMenuList(@PathVariable String id, @RequestBody MenuDTO dto){
        dto.setParentId(id);
        List<MenuEntity> result = menuService.getList(dto);
        return RestResult.success(result);
    }

    @ApiOperation("获取菜单树")
    @GetMapping(value = "getMenuTree.do")
    protected RestResult getMenuTree(MenuAuthorityDTO dto){
        List<TreeDTO> result = menuService.getMenuTree(dto);
        return RestResult.success(result);
    }

    @ApiOperation("获取按钮列表")
    @PostMapping(value = "getButtonList.do")
    protected RestResult getButtonList(MenuDTO dto){
        MenuDTO result = menuService.getButtonList(dto);
        return RestResult.success(result);
    }
}
