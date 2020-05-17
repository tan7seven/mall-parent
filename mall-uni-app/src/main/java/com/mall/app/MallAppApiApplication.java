package com.mall.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication(scanBasePackages="com.mall")
public class MallAppApiApplication {

	public static void main(String[] args) {
        System.out.println("SpringBoot      ==>开始启动");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(MallAppApiApplication.class, args);
        System.out.println("SpringBoot      ==>启动成功");
	}

}
