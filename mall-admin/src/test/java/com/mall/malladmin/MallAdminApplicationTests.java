package com.mall.malladmin;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.order.OrderDTO;
import com.mall.malladmin.service.order.OrderService;
import com.mall.malladmin.service.product.ProductDetailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallAdminApplicationTests {
    @Resource(name = "productDetailService")
    private ProductDetailService productDetailService;
    @Resource(name = "orderService")
    private OrderService orderService;
	@Test
	public void contextLoads( ) {
        OrderDTO dto = new OrderDTO();
        PageInfo<OrderDTO> result = orderService.getPage(dto);
        log.info("结果：{}",result);
	}

}
