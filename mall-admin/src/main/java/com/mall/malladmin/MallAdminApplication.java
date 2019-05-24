package com.mall.malladmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.mall.malladmin")
@MapperScan(value="com.mall.malladmin.mapper")
public class MallAdminApplication {

	public static void main(String[] args) {
        System.out.println("SpringBoot      ==>开始启动");
	    SpringApplication.run(MallAdminApplication.class, args);
        System.out.println("SpringBoot      ==>启动成功");
	}

}
