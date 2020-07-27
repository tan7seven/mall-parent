package com.mall.manage.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.manage.model.param.system.menu.ButtonAuthConfirmParam;
import com.mall.manage.model.param.system.menu.MenuAuthConfirmParam;
import com.mall.manage.security.UserDetailsImpl;

import java.util.List;

/**
 * 用户信息service
 */
public interface AdminService extends IService<AdminEntity> {

    /**
     * 分页查询
     */
    Page<AdminEntity> getPage(Integer pageNum, Integer pageSize);
    /**
     * 根据登录号码获取账号信息
     */
    AdminEntity findByLoginCode(String loginCode);
    /**
     * 菜单授权
     */
    Boolean menuAuthority(MenuAuthConfirmParam param);
    /**
     * 按钮授权
     */
    Boolean buttonAuthority(ButtonAuthConfirmParam param);
    /**
     * 根据用户ID获取已授权菜单列表ID
     */
    List<Long> getAdminMenuAuthority(String userId);
    // todo done
    /**
     * 重新加载用户信息
     * @return
     */
    UserDetailsImpl adminLogin(String username);
}
