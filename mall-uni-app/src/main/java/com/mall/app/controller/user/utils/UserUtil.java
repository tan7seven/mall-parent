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
        result.setPortrait(entity.getPortrait());
        result.setNickname(entity.getNickname());
        result.setToken(token);
        result.setMobile(entity.getMobile());
        return result;
    }
}
