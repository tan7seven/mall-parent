package com.mall.malladmin.mapper.system;

import com.mall.malladmin.dto.system.AdminDto;
import org.apache.ibatis.annotations.Param;

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
}
