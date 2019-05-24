package com.mall.malladmin.mapper;

import com.mall.malladmin.vo.AdminVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户mapper
 */
public interface AdminMapper {
    /**
     * 根据登录账号获取用户信息
     * @param loginId
     * @return
     */
    AdminVo findByLoginId(@Param("loginId") String loginId);
}
