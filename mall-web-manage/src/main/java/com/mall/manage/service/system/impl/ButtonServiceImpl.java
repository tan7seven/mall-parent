package com.mall.manage.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.system.ButtonEntity;
import com.mall.dao.mapper.system.ButtonMapper;
import com.mall.manage.service.system.ButtonService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/3/20
 */
@Service
public class ButtonServiceImpl  extends ServiceImpl<ButtonMapper, ButtonEntity> implements ButtonService {
}
