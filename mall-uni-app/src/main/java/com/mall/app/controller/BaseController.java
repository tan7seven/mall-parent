package com.mall.app.controller;

import com.mall.app.handler.BaseContextHandler;
import com.mall.common.enums.ResultStatus;
import com.mall.common.exception.BusinessException;

import java.util.Objects;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/29
 */

public class BaseController {

    public Long getUserId(){
        Long userID = BaseContextHandler.getUserID();
        if (Objects.isNull(userID)) {
            throw new BusinessException(ResultStatus.USER_NEED_AUTHORITIES);
        }
        return userID;
    }
}
