package com.mall.malladmin;

import com.alibaba.fastjson.JSONObject;
import com.mall.malladmin.entity.product.ProductDetailEntity;
import com.mall.malladmin.service.product.ProductDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallAdminApplicationTests {
    @Resource(name = "productDetailService")
    private ProductDetailService productDetailService;
	@Test
	public void contextLoads() {
        ProductDetailEntity entity = new ProductDetailEntity();
        // 先根据状态倒序排列，再根据创建时间倒序排序，再分页。
        List<Sort.Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "_state"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "createtime"));
        //Sort sort = new Sort(Sort.Direction.DESC,"createtime");
        Sort.by(orders);
        entity.setDetail("service添加测试");
        entity.setProductId(1);
        Pageable page =new PageRequest(0,2,Sort.by(orders));
        Page<ProductDetailEntity> result = productDetailService.findPage(entity,page);
        System.out.println(JSONObject.toJSONString(result));
        System.out.println(JSONObject.toJSONString(result.getContent()));
	}

}
