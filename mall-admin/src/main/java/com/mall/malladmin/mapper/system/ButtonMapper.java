package com.mall.malladmin.mapper.system;

import com.mall.malladmin.dto.system.AdminDTO;
import com.mall.malladmin.dto.system.ButtonDTO;
import com.mall.malladmin.dto.system.MenuDTO;
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
    List<ButtonDTO> getListAuthority(@Param("dto") MenuDTO dto);

    /**
     * 根据用户编码跟菜单ID删除
     * @param dto
     * @return
     */
    int deleteByMenuIdAndUserId(@Param("dto") AdminDTO dto);

}
