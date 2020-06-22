package com.mall.app.controller.advert;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.app.controller.advert.utils.AdvertUtil;
import com.mall.app.model.vo.advert.AdvertVO;
import com.mall.app.service.advert.AdvertProductService;
import com.mall.app.service.advert.AdvertService;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.advert.AdvertEntity;
import com.mall.dao.entity.advert.AdvertProductEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "首页")
@RestController
@RequestMapping(value = "/v1/home")
public class HomeController {

    @Autowired
    private AdvertService advertService;
    @Autowired
    private AdvertProductService advertProductService;

    @ApiOperation(value = "广告列表")
    @GetMapping(value = "/advert/list")
    public RestResult<List<AdvertVO>> advertList(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                     @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize, false);
        Page<AdvertEntity> advertPage = (Page<AdvertEntity>) advertService.page(pageParam);
        List<AdvertVO> result = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(advertPage.getRecords())) {
            return RestResult.success(result);
        }
        List<Long> advertIdList = advertPage.getRecords().stream().map(s -> s.getId()).collect(Collectors.toList());
        List<AdvertProductEntity> productList = advertProductService.list(Wrappers.<AdvertProductEntity>lambdaQuery().in(AdvertProductEntity::getAdvertId, advertIdList));
        result = AdvertUtil.buildAdvertVO(advertPage.getRecords(), productList);
        return RestResult.success(result);
    }
}
