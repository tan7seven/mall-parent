package com.mall.malladmin.mapper;

import com.mall.malladmin.vo.AdminVo;

/**
 * 用户mapper
 */
public interface AdminMapper {
    /**
     * 根据登录账号获取用户信息
     * @param loginId
     * @return
     */
    AdminVo findByLoginId(String loginId);
}
