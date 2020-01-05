package com.mall.malladmin.controller.orders;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.orders.OrdersReturnApplyDto;
import com.mall.malladmin.service.orders.OrdersReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "售后订单", tags = "售后订单")
@RestController
@RequestMapping(value = "ordersReturnApplyController")
public class OrdersReturnApplyController extends GenericController {

    @Resource(name = "ordersReturnApplyService")
    private OrdersReturnApplyService ordersReturnApplyService;

    @ApiOperation("分页查询")
    @PostMapping(value = "getPage.do")
    protected Object getPage(OrdersReturnApplyDto dto){
        PageInfo<OrdersReturnApplyDto> result = ordersReturnApplyService.getPage(dto);
        return new CommonResultDto().success(result);
    }

    @ApiOperation("获取订单退货申请明细")
    @GetMapping(value = "getApplyById.do/{id}")
    protected  CommonResultDto getApplyById(@PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDto().validateFailed("编号为空！");
        }
        OrdersReturnApplyDto result = ordersReturnApplyService.findById(id);
        return new CommonResultDto().success(result);
    }

    @ApiOperation("修改订单退货信息")
    @PreAuthorize(" hasAuthority('OMS:RETURNAPPLY:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateApplyStatus.do/{id}")
    protected CommonResultDto updateApplyStatus(@PathVariable String id, @RequestBody OrdersReturnApplyDto dto){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDto().validateFailed("编码为空！");
        }
        dto.setApplyId(id);
        ordersReturnApplyService.updateApplyStatus(dto, this.getUserDetails());
        return new CommonResultDto().success();
    }

    @ApiOperation("批量删除退货申请单")
    @PreAuthorize(" hasAuthority('OMS:RETURNAPPLY:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteApply.do")
    protected CommonResultDto deleteApply(List<String> ids){
        if(null == ids || StringUtils.isBlank(ids.toString())){
            return new CommonResultDto().success();
        }
        ordersReturnApplyService.deleteApply(ids);
        return new CommonResultDto().success();
    }
}
