package com.mall.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult  implements Serializable {
    private static final long serialVersionUID = 2302873019826104491L;
    public static final String DEFAULT_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final int SUCCESS = 200;
    public static final int FAIL = 500;
    private int status;
    private String message;
}
