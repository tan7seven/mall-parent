package com.mall.app.service.advert.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.advert.AdvertProductService;
import com.mall.dao.entity.advert.AdvertProductEntity;
import com.mall.dao.mapper.advert.AdvertProductMapper;
import org.springframework.stereotype.Service;

@Service
public class AdvertProductServiceImpl extends ServiceImpl<AdvertProductMapper, AdvertProductEntity> implements AdvertProductService {
}
