package com.mall.manage.controller.advert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.constant.CommonConstant;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.advert.AdvertProductEntity;
import com.mall.manage.controller.advert.utils.AdvertProductUtil;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.model.param.advert.AdvertProductParam;
import com.mall.manage.model.vo.advert.AdvertProductVO;
import com.mall.manage.service.advert.AdvertProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/6/1
 */
@Slf4j
@Api(value = "广告商品模块", tags = "广告商品模块")
@RestController
@RequestMapping(value = "/advert-product")
public class AdvertProductController extends GenericController {

    @Autowired
    private AdvertProductService advertProductService;

    @ApiOperation("广告列表")
    @GetMapping(value = "/list")
    public RestResult<RestPage<AdvertProductVO>> list(@ApiParam(value = "页码") @RequestParam Long advertId,
                                                      @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize);
        QueryWrapper<AdvertProductEntity> wrapper = new QueryWrapper();
        wrapper.eq("advert_id", advertId);
        Page<AdvertProductEntity> advertPage = (Page<AdvertProductEntity>) advertProductService.page(pageParam, wrapper);
        RestPage<AdvertProductVO> result = AdvertProductUtil.buildAdvertProductVOList(advertPage);
        return RestResult.success(result);
    }

    @ApiOperation("新增")
    @PostMapping(value = "/create")
    public RestResult<Boolean> create(@RequestBody @Validated AdvertProductParam param){
        AdvertProductEntity entity = new AdvertProductEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setPicUrl(entity.getPicUrl().replaceAll(CommonConstant.IMG_PRE, ""));
        Boolean result = advertProductService.save(entity);
        return RestResult.success(result);
    }

    @ApiOperation("修改")
    @PostMapping(value = "/modify")
    public RestResult<Boolean> modify(@RequestBody @Validated AdvertProductParam param){
        if (null == param.getId()) {
            throw  new BusinessException("ID不能为空");
        }
        AdvertProductEntity entity = new AdvertProductEntity();
        BeanUtils.copyProperties(param, entity);
        entity.setId(param.getId());
        entity.setPicUrl(entity.getPicUrl().replaceAll(CommonConstant.IMG_PRE, ""));
        Boolean result = advertProductService.updateById(entity);
        return RestResult.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResult<Boolean> delete(@PathVariable Long id){
        Boolean result = advertProductService.removeById(id);
        return RestResult.success(result);
    }

    @ApiOperation("详情")
    @GetMapping(value = "/detail/{id}")
    public RestResult<AdvertProductVO> detail(@PathVariable Long id){
        AdvertProductEntity entity = advertProductService.getById(id);
        AdvertProductVO result = new AdvertProductVO();
        BeanUtils.copyProperties(entity, result);
        if (StringUtils.isNotBlank(result.getPicUrl())) {
            result.setPicUrl(CommonConstant.IMG_PRE+result.getPicUrl());
        }
        return RestResult.success(result);
    }
}
