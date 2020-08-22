package com.mall.common.excel;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2020/7/11
 */

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumnO2O {

    String value() default "";

    int sort() default 0;

    ExcelCellTypeEnum type() default ExcelCellTypeEnum.COMMON;
}
