package com.mall.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@SpringBootApplication()
@MapperScan(value= "com.mall.dao.mapper")
@ComponentScan(basePackages={"com.mall"})
public class ManageApplication {

	public static void main(String[] args) {
        System.out.println("SpringBoot      ==>开始启动");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	    SpringApplication.run(ManageApplication.class, args);
        System.out.println("SpringBoot      ==>启动成功");
	}

}
