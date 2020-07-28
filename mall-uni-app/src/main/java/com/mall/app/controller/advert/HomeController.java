package com.mall.app.controller.advert;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.app.controller.advert.utils.AdvertUtil;
import com.mall.app.model.vo.advert.AdvertCarouseVO;
import com.mall.app.model.vo.advert.AdvertCateVO;
import com.mall.app.model.vo.advert.AdvertVO;
import com.mall.app.service.advert.AdvertProductService;
import com.mall.app.service.advert.AdvertService;
import com.mall.common.enums.AdvertTypeEnum;
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
        if (CollectionUtils.isEmpty(advertPage.getRecords())) {
            return RestResult.success(result);
        }
        List<Long> advertIdList = advertPage.getRecords().stream().map(s -> s.getId()).collect(Collectors.toList());
        List<AdvertProductEntity> productList = advertProductService.list(Wrappers.<AdvertProductEntity>lambdaQuery().in(AdvertProductEntity::getAdvertId, advertIdList));
        result = AdvertUtil.buildAdvertVO(advertPage.getRecords(), productList);
        return RestResult.success(result);
    }

    @ApiOperation(value = "广告列表-旋转木马")
    @GetMapping(value = "/advert-carouse/list")
    public RestResult<List<AdvertCarouseVO>> advertCarouseList(){
        List<AdvertEntity> entityList = advertService.list(Wrappers.<AdvertEntity>lambdaQuery().eq(AdvertEntity::getType, AdvertTypeEnum.HOME_CAROUSEL.getCode()));
        List<AdvertCarouseVO> result = AdvertUtil.buildAdvertCarouseVO(entityList);
        return RestResult.success(result);
    }

    @ApiOperation(value = "广告列表-分类")
    @GetMapping(value = "/advert-cate/list")
    public RestResult<List<AdvertCateVO>> advertCateList(){
        List<AdvertEntity> entityList = advertService.list(Wrappers.<AdvertEntity>lambdaQuery().eq(AdvertEntity::getType, AdvertTypeEnum.HOME_TYPE.getCode()));
        List<AdvertCateVO> result = AdvertUtil.buildAdvertCateVO(entityList);
        return RestResult.success(result);
    }

    @ApiOperation(value = "广告列表-分类精选")
    @GetMapping(value = "/advert-hot-floor/list")
    public RestResult<List<AdvertVO>> advertHotFloorList(){
        List<AdvertEntity> entityList = advertService.list(Wrappers.<AdvertEntity>lambdaQuery().eq(AdvertEntity::getType, AdvertTypeEnum.HOME_TYPE_PRODUCT.getCode()));
        List<AdvertVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(entityList)) {
            return RestResult.success(result);
        }
        List<Long> advertIdList = entityList.stream().map(s -> s.getId()).collect(Collectors.toList());
        List<AdvertProductEntity> productList = advertProductService.list(Wrappers.<AdvertProductEntity>lambdaQuery().in(AdvertProductEntity::getAdvertId, advertIdList));
        result  = AdvertUtil.buildAdvertVO(entityList, productList);
        return RestResult.success(result);
    }

    @ApiOperation(value = "广告列表-猜你喜欢")
    @GetMapping(value = "/advert-guess/list")
    public RestResult<List<AdvertVO>> advertGuessList(){
        List<AdvertEntity> entityList = advertService.list(Wrappers.<AdvertEntity>lambdaQuery().eq(AdvertEntity::getType, AdvertTypeEnum.HOME_GUESS_LIKE.getCode()));
        List<AdvertVO> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(entityList)) {
            return RestResult.success(result);
        }
        List<Long> advertIdList = entityList.stream().map(s -> s.getId()).collect(Collectors.toList());
        List<AdvertProductEntity> productList = advertProductService.list(Wrappers.<AdvertProductEntity>lambdaQuery().in(AdvertProductEntity::getAdvertId, advertIdList));
        result  = AdvertUtil.buildAdvertVO(entityList, productList);
        return RestResult.success(result);
    }
}
