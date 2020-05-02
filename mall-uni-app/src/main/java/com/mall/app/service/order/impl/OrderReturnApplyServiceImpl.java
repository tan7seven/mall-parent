package com.mall.app.service.order.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.app.service.order.OrderReturnApplyService;
import com.mall.dao.entity.order.OrderReturnApplyEntity;
import com.mall.dao.mapper.order.OrderReturnApplyMapper;
import org.springframework.stereotype.Service;

@Service(value = "orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {

}
