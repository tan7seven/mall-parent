package com.mall.manage.controller.order;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.vo.RestResult;
import com.mall.dao.dto.order.OrderReturnApplyDTO;
import com.mall.manage.controller.common.GenericController;
import com.mall.manage.service.order.OrderReturnApplyService;
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
    protected RestResult<Page<OrderReturnApplyDTO>> getPage(OrderReturnApplyDTO dto){
        Page<OrderReturnApplyDTO> result = orderReturnApplyService.getPage(dto);
        return RestResult.success(result);
    }

    @ApiOperation("获取订单退货申请明细")
    @GetMapping(value = "getApplyById.do/{id}")
    protected RestResult getApplyById(@PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return RestResult.validateFailed("编号为空！");
        }
        OrderReturnApplyDTO result = orderReturnApplyService.findById(id);
        return RestResult.success(result);
    }

    @ApiOperation("修改订单退货信息")
    @PreAuthorize(" hasAuthority('OMS:RETURNAPPLY:UPDATE') or hasRole('ADMIN')")
    @PostMapping(value = "updateApplyStatus.do/{id}")
    protected RestResult updateApplyStatus(@PathVariable String id, @RequestBody OrderReturnApplyDTO dto){
        if (StringUtils.isBlank(id)) {
            return RestResult.validateFailed("编码为空！");
        }
        dto.setApplyId(id);
        orderReturnApplyService.updateApplyStatus(dto, this.getUserDetails());
        return RestResult.success();
    }

    @ApiOperation("批量删除退货申请单")
    @PreAuthorize(" hasAuthority('OMS:RETURNAPPLY:DELETE') or hasRole('ADMIN')")
    @PostMapping(value = "deleteApply.do")
    protected RestResult deleteApply(List<String> ids){
        if(null == ids || StringUtils.isBlank(ids.toString())){
            return RestResult.success();
        }
        orderReturnApplyService.deleteApply(ids);
        return RestResult.success();
    }
}
