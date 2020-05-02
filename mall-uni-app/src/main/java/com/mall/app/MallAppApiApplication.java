package com.mall.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages="com.mall")
@EntityScan("com.mall")
public class MallAppApiApplication {

	public static void main(String[] args) {
        System.out.println("SpringBoot      ==>开始启动");
        SpringApplication.run(MallAppApiApplication.class, args);
        System.out.println("SpringBoot      ==>启动成功");
	}

}
