package com.mall.app;

import com.mall.common.enums.OrderEventEnum;
import com.mall.common.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;

import java.util.TimeZone;

@EnableStateMachine
@SpringBootApplication
public class MallAppApiApplication {



	public static void main(String[] args) {
        System.out.println("SpringBoot      ==>开始启动");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(MallAppApiApplication.class, args);
        System.out.println("SpringBoot      ==>启动成功");
	}

}
