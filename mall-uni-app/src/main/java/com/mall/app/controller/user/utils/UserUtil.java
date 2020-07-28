package com.mall.app.controller.user.utils;

import com.mall.app.model.vo.user.UserLoginVO;
import com.mall.dao.entity.user.UserEntity;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/28
 */
public class UserUtil {
    /**
     * 登录放回参数
     */
    public static UserLoginVO buildUserLoginVO(UserEntity entity, String token){
        UserLoginVO result = new UserLoginVO();
        result.setHeadPortrait(entity.getHeadPortrait());
        result.setName(entity.getName());
        result.setToken(token);
        return result;
    }
}
