package com.mall.malladmin.controller.system;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.common.TreeDto;
import com.mall.malladmin.dto.system.MenuAuthorityDto;
import com.mall.malladmin.dto.system.MenuDto;
import com.mall.malladmin.entity.system.MenuEntity;
import com.mall.malladmin.service.system.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 菜单
 */
@Slf4j
@RestController
@RequestMapping(value = "/menuController")
public class MenuController extends GenericController {

    @Resource(name = "menuService")
    private MenuService menuService;

    /**
     *分页查询
     * @param dto
     * @return
     */
    @PostMapping(value = "getPage.do")
    protected Object getPage(MenuDto dto){
        PageInfo<MenuDto> result = menuService.getPage(dto);
        return new CommonResultDto().success(result);
    }
    /**
     *分页查询
     * @param menuId
     * @return
     */
    @PostMapping(value = "getMenuListById.do")
    protected  Object getMenuListById(String menuId){
       List<MenuDto> result =  menuService.getListById(menuId);
        return new CommonResultDto().success(result);
    }

    /**
     * 获取信息
     * @param id
     * @return
     */
    @GetMapping(value = "/getMenuInfo.do/{id}")
    protected  Object getMenuInfo(@PathVariable String id){
        MenuDto result = menuService.findById(id);
        return new CommonResultDto().success(result);
    }

    /**
     * 新建
     * @param dto
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:CREATE') or hasRole('ADMIN')")
    @PostMapping(value = "createMenu.do")
    protected Object createMenu(@RequestBody MenuDto dto){
        menuService.add(dto);
        return new CommonResultDto().success();
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateMenu.do/{id}")
    protected Object updateMenu(@RequestBody MenuDto dto,@PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDto().validateFailed("ID为空！");
        }
        menuService.update(dto, id);
        return new CommonResultDto().success();
    }

    /**
     * 删除
     */
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteMenu.do")
    protected Object deleteMenu(List<String> ids){
        if(null == ids || ids.size()==0){
            return new CommonResultDto().validateFailed("id为空！");
        }
        menuService.deleteMenu(ids);
        return new CommonResultDto().success();
    }

    /**
     * 是否隐藏
     * @return
     */
    @PreAuthorize(" hasAuthority('SYSTEM:MENU:SWITCH') or hasRole('ADMIN')")
    @PostMapping(value = "updateIsHidden.do")
    protected Object updateIsHidden(@RequestBody MenuDto dto){
        if(StringUtils.isBlank(dto.getMenuId())){
            return new CommonResultDto().validateFailed("用户编码为空！");
        }
        menuService.updateIsHidden(dto);
        return new CommonResultDto().success();
    }

    /**
     * 获取父级ID获取菜单列表
     * @param id
     * @param dto
     * @return
     */
    @PostMapping(value = "getMenuList.do/{id}")
    protected Object getMenuList(@PathVariable String id, @RequestBody MenuDto dto){
        dto.setParentId(id);
        List<MenuEntity> result = menuService.getList(dto);
        return new CommonResultDto().success(result);
    }

    /**
     * 获取菜单树
     * @return
     */
    @GetMapping(value = "getMenuTree.do")
    protected Object getMenuTree(MenuAuthorityDto dto){
        List<TreeDto> result = menuService.getMenuTree(dto);
        return new CommonResultDto().success(result);
    }

    /**
     * 获取按钮列表
     * @param dto
     * @return
     */
    @PostMapping(value = "getButtonList.do")
    protected Object getButtonList(MenuDto dto){
        MenuDto result = menuService.getButtonList(dto);
        return new CommonResultDto().success(result);
    }
}
