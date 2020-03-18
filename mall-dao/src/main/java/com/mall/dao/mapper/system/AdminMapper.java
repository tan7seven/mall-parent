package com.mall.dao.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.entity.product.ProductPropertyNameEntity;
import com.mall.dao.entity.system.AdminEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 */
public interface AdminMapper extends BaseMapper<AdminEntity> {
    /**
     * 根据登录账号获取用户信息
     * @param loginCode
     * @return
     */
    AdminDTO findByLoginId(@Param("loginCode") String loginCode);

    /**
     * 获取用户按钮权限编码列表
     * @param dto
     * @return
     */
    List<String> getButtonCodeAuthority(@Param("dto") AdminDTO dto);
    /**
     * 获取菜单按钮权限编码列表
     * @param dto
     * @return
     */
    List<String> getMenuCodeListAuthority(@Param("dto") AdminDTO dto);
}
