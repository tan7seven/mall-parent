package com.mall.malladmin.controller.orders;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.controller.common.GenericController;
import com.mall.malladmin.dto.common.CommonResultDto;
import com.mall.malladmin.dto.orders.OrdersReturnApplyDto;
import com.mall.malladmin.service.orders.OrdersReturnApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单退货申请
 */
@RestController
@RequestMapping(value = "ordersReturnApplyController")
public class OrdersReturnApplyController extends GenericController {

    @Resource(name = "ordersReturnApplyService")
    private OrdersReturnApplyService ordersReturnApplyService;

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @PostMapping(value = "getPage.do")
    protected Object getPage(OrdersReturnApplyDto dto){
        PageInfo<OrdersReturnApplyDto> result = ordersReturnApplyService.getPage(dto);
        return new CommonResultDto().success(result);
    }

    /**
     * 获取订单退货申请明细
     * @return
     */
    @GetMapping(value = "getApplyById.do/{id}")
    protected  Object getApplyById(@PathVariable String id){
        if (StringUtils.isBlank(id)) {
            return new CommonResultDto().validateFailed("编号为空！");
        }
        OrdersReturnApplyDto result = ordersReturnApplyService.findById(id);
        return new CommonResultDto().success(result);
    }
}
