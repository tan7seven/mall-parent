package com.mall.malladmin.mapper.system;

import com.mall.malladmin.dto.system.AdminDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 */
public interface AdminMapper {
    /**
     * 根据登录账号获取用户信息
     * @param loginCode
     * @return
     */
    AdminDto findByLoginId(@Param("loginCode") String loginCode);

    /**
     * 获取用户按钮权限编码列表
     * @param dto
     * @return
     */
    List<String> getButtonCodeAuthority(@Param("dto") AdminDto dto);
    /**
     * 获取菜单按钮权限编码列表
     * @param dto
     * @return
     */
    List<String> getMenuCodeListAuthority(@Param("dto") AdminDto dto);
}