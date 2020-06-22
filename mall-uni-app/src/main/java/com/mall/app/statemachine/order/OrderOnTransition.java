package com.mall.app.statemachine.order;

import com.mall.common.enums.OrderStatusEnum;
import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huanggs
 * @date 2020/5/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface OrderOnTransition {
    OrderStatusEnum[] source() default {};

    OrderStatusEnum[] target() default {};
}
