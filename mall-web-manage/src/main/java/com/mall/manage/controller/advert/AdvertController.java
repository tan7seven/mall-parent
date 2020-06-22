package com.mall.manage.controller.advert;

import com.alibaba.acm.shaded.com.google.common.collect.Maps;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.mall.common.constant.CommonConstant;
import com.mall.common.enums.AdvertTypeEnum;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.advert.AdvertEntity;
import com.mall.manage.controller.advert.utils.AdvertUtil;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.model.param.advert.AdvertParam;
import com.mall.manage.model.vo.advert.AdvertVO;
import com.mall.manage.service.advert.AdvertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/1
 */
@Slf4j
@Api(value = "广告模块", tags = "广告模块")
@RestController
@RequestMapping(value = "/advert")
public class AdvertController extends GenericController {

    @Autowired
    private AdvertService advertService;

    @ApiOperation("广告列表")
    @GetMapping(value = "/list")
    public RestResult<RestPage<AdvertVO>> list(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize);
        QueryWrapper<AdvertEntity> wrapper = new QueryWrapper();
        Page<AdvertEntity> advertPage = (Page<AdvertEntity>) advertService.page(pageParam);
        RestPage<AdvertVO> result = AdvertUtil.buildAdvertVOList(advertPage);
        return RestResult.success(result);
    }

    @ApiOperation("广告类型列表")
    @GetMapping(value = "/type-enum/list")
    public RestResult<List<Map<String, Object>>> getAdvertTypeList(){
        List<Map<String, Object>> result = Lists.newArrayList();
        for (AdvertTypeEnum value : AdvertTypeEnum.values()) {
            Map<String, Object> voMap = Maps.newHashMap();
            voMap.put("label", value.getDesc());
            voMap.put("value", value.getCode());
            result.add(voMap);
        }
        return RestResult.success(result);
    }

    @ApiOperation("新增")
    @PostMapping(value = "/create")
    public RestResult<Boolean> create(@RequestBody @Validated AdvertParam param){
        AdvertEntity advertEntity = new AdvertEntity();
        BeanUtils.copyProperties(param, advertEntity);
        advertEntity.setPicUrl(advertEntity.getPicUrl().replaceAll(CommonConstant.IMG_PRE, ""));
        Boolean result = advertService.save(advertEntity);
        return RestResult.success(result);
    }

    @ApiOperation("修改")
    @PostMapping(value = "/modify")
    public RestResult<Boolean> modify(@RequestBody @Validated AdvertParam param){
        if (null == param.getId()) {
            throw  new BusinessException("ID不能为空");
        }
        AdvertEntity advertEntity = new AdvertEntity();
        BeanUtils.copyProperties(param, advertEntity);
        advertEntity.setId(param.getId());
        advertEntity.setPicUrl(advertEntity.getSkipUrl().replaceAll(CommonConstant.IMG_PRE, ""));
        Boolean result = advertService.updateById(advertEntity);
        return RestResult.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResult<Boolean> delete(@PathVariable Long id){
        Boolean result = advertService.removeById(id);
        return RestResult.success(result);
    }

    @ApiOperation("详情")
    @GetMapping(value = "/detail/{id}")
    public RestResult<AdvertVO> detail(@PathVariable Long id){
        AdvertEntity entity = advertService.getById(id);
        AdvertVO result = new AdvertVO();
        BeanUtils.copyProperties(entity, result);
        if (StringUtils.isNotBlank(result.getPicUrl())) {
            result.setPicUrl(CommonConstant.IMG_PRE+result.getPicUrl());
        }
        return RestResult.success(result);
    }
}
