package com.mall.malladmin.controller.order;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDTO;
import com.mall.malladmin.dto.order.OrderReturnApplyDTO;
import com.mall.malladmin.service.order.OrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "售后订单", tags = "售后订单")
@RestController
@RequestMapping(value = "orderReturnApplyController")
public class OrderReturnApplyController extends GenericController {

    @Resource(name = "orderReturnApplyService")
    private OrderReturnApplyService orderReturnApplyService;

    @ApiOperation("分页查询")
    @PostMapping(value = "getPage.do")
    protected Object getPage(OrderReturnApplyDTO dto){
        PageInfo<OrderReturnApplyDTO> result = orderReturnApplyService.getPage(dto);
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("获取订单退货申请明细")
    @GetMapping(value = "getApplyById.do/{id}")
    protected CommonResultDTO getApplyById(@PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDTO().validateFailed("编号为空！");
        }
        OrderReturnApplyDTO result = orderReturnApplyService.findById(id);
        return new CommonResultDTO().success(result);
    }

    @ApiOperation("修改订单退货信息")
    @PreAuthorize(" hasAuthority('OMS:RETURNAPPLY:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateApplyStatus.do/{id}")
    protected CommonResultDTO updateApplyStatus(@PathVariable String id, @RequestBody OrderReturnApplyDTO dto){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDTO().validateFailed("编码为空！");
        }
        dto.setApplyId(id);
        orderReturnApplyService.updateApplyStatus(dto, this.getUserDetails());
        return new CommonResultDTO().success();
    }

    @ApiOperation("批量删除退货申请单")
    @PreAuthorize(" hasAuthority('OMS:RETURNAPPLY:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteApply.do")
    protected CommonResultDTO deleteApply(List<String> ids){
        if(null == ids || StringUtils.isBlank(ids.toString())){
            return new CommonResultDTO().success();
        }
        orderReturnApplyService.deleteApply(ids);
        return new CommonResultDTO().success();
    }
}
