package com.mall.app.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.app.controller.user.utils.UserAddressUtil;
import com.mall.app.model.param.user.AddressParam;
import com.mall.app.model.vo.user.AddressVO;
import com.mall.app.service.user.UserAddressService;
import com.mall.common.exception.BusinessException;
import com.mall.common.model.vo.RestPage;
import com.mall.common.model.vo.RestResult;
import com.mall.dao.entity.user.UserAddressEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "用户收货地址模块")
@RestController
@RequestMapping(value = "/v1/user-address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "列表")
    @GetMapping(value = "/list")
    public RestResult<RestPage<AddressVO>> list(@ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer pageNum,
                                               @ApiParam(value = "页数") @RequestParam(defaultValue = "20") Integer pageSize){
        Page pageParam = new Page(pageNum, pageSize, false);
        Long userId = 123L;
        QueryWrapper<UserAddressEntity> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        Page<UserAddressEntity> productPage = (Page<UserAddressEntity>) userAddressService.page(pageParam, wrapper);
        RestPage<AddressVO> result = UserAddressUtil.buildListVO(productPage);
        return RestResult.success(result);

    }
    @ApiOperation(value = "新增")
    @PutMapping(value = "/create")
    public RestResult<Boolean> create(@Validated @RequestBody AddressParam param){
        Long userId = 123L;
        Boolean result = userAddressService.addAddress(param, userId);
        return RestResult.success(result);
    }


    @ApiOperation(value = "修改")
    @PostMapping(value = "/modify")
    public RestResult<Boolean> modify(@Validated @RequestBody AddressParam param){
        if (null == param.getId()) {
            throw new BusinessException("ID不能为空");
        }
        Long userId = 123L;
        Boolean result = userAddressService.modifyAddress(param, userId);
        return RestResult.success(result);
    }
}
