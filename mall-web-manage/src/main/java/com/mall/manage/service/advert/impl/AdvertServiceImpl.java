package com.mall.manage.service.advert.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.entity.advert.AdvertEntity;
import com.mall.dao.mapper.advert.AdvertMapper;
import com.mall.manage.service.advert.AdvertService;
import org.springframework.stereotype.Service;

@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, AdvertEntity> implements AdvertService {
}
