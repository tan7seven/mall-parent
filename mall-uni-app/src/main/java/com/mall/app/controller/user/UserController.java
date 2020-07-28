package com.mall.app.controller.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mall.app.controller.user.utils.UserUtil;
import com.mall.app.jwt.JwtTokenUtil;
import com.mall.app.model.param.user.UserLoginParam;
import com.mall.app.model.vo.user.UserLoginVO;
import com.mall.app.service.user.UserService;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.user.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/5/28
 */
@Slf4j
@Api(value = "用户模块")
@RestController
@RequestMapping(value = "/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public RestResult<UserLoginVO> userLogin(@Validated @RequestBody UserLoginParam param){
        UserEntity entity = userService.getOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getPhone, param));
        if (!entity.getPassword().equals(param.getPassword())) {
            throw new BusinessException("密码或账号错误");
        }
        String token = JwtTokenUtil.generateToken(entity);
        return RestResult.success(UserUtil.buildUserLoginVO(entity, token));
    }

}
