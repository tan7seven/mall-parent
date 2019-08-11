package com.mall.mallwechar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages="com.mall.mallwechar")
@EntityScan("com.mall.mallmodel")
public class MallWecharApplication {

	public static void main(String[] args) {
        System.out.println("SpringBoot      ==>开始启动");
        SpringApplication.run(MallWecharApplication.class, args);
        System.out.println("SpringBoot      ==>启动成功");
	}

}
