package com.mall.manage.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dao.dto.system.AdminDTO;
import com.mall.dao.entity.system.AdminEntity;
import com.mall.manage.model.param.system.MenuAuthConfirmParam;
import com.mall.manage.security.UserDetailsImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    // todo done
    /**
     * 重新加载用户信息
     * @return
     */
    UserDetailsImpl adminLogin(String username);

    /**
     * 根据登录账号获取用户信息
     * @param loginCode
     * @return
     */
    AdminDTO findByLoginId(String loginCode);

    /**
     * 删除（逻辑删除）
     * @param ids
     */
    void deleteAdmin(List<String> ids);

    /**
     * 是否可用
     * @param dto
     */
    void updateIsUsable(AdminDTO dto);




    /**
     * 菜单授权
     * @param dto
     */
    @Transactional
    void buttonAuthority(AdminDTO dto);
    /**
     * 根据用户ID获取已授权菜单列表ID
     * @param userId
     * @return
     */
    List<Long> getAdminMenuAuthority(String userId);

}
