package com.mall.malladmin;

import com.github.pagehelper.PageInfo;
import com.mall.malladmin.dto.orders.OrdersDto;
import com.mall.malladmin.service.orders.OrdersService;
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
    @Resource(name = "ordersService")
    private OrdersService ordersService;
	@Test
	public void contextLoads( ) {
        OrdersDto dto = new OrdersDto();
        PageInfo<OrdersDto> result = ordersService.getPage(dto);
        log.info("结果：{}",result);
	}

}
