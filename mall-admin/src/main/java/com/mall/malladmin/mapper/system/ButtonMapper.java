package com.mall.malladmin.mapper.system;

import com.mall.malladmin.dto.system.AdminDto;
import com.mall.malladmin.dto.system.ButtonDto;
import com.mall.malladmin.dto.system.MenuDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单列表
 */
public interface ButtonMapper {

    /**
     * 获取列表-已分配权限
     * @param dto
     * @return
     */
    List<ButtonDto> getListAuthority(@Param("dto") MenuDto dto);

    /**
     * 根据用户编码跟菜单ID删除
     * @param dto
     * @return
     */
    int deleteByMenuIdAndUserId(@Param("dto")AdminDto dto);

}
