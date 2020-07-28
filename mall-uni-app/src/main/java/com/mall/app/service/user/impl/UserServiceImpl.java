package com.mall.app.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.user.UserService;
import com.mall.dao.entity.user.UserEntity;
import com.mall.dao.mapper.user.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
