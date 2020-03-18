package com.mall.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult <T> extends BaseResult {
    //操作成功
    public static final int SUCCESS = 200;
    //操作失败
    public static final int FAILED = 500;
    //参数校验失败
    public static final int VALIDATE_FAILED = 402;
    //未认证
    public static final int UNAUTHORIZED = 401;
    //未授权
    public static final int  FORBIDDEN = 403;
    private int code;
    private String message;
    private Object data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public static <T> RestResult<T>  success(T data) {
        RestResult restResult = new RestResult();
        restResult.code = SUCCESS;
        restResult.message = "操作成功";
        restResult.data = data;
        return restResult;
    }
    /**
     * 普通成功返回
     *
     */
    public static RestResult success() {
        RestResult restResult = new RestResult();
        restResult.code = SUCCESS;
        restResult.message = "操作成功";
        return restResult;
    }

    /**
     * 普通失败提示信息
     */
    public static RestResult failed() {
        RestResult restResult = new RestResult();
        restResult.code = FAILED;
        restResult.message = "操作失败";
        return restResult;
    }
    /**
     * 普通失败提示信息
     */
    public static RestResult failed(String message) {
        RestResult restResult = new RestResult();
        restResult.code = FAILED;
        restResult.message = message;
        return restResult;
    }
    /**
     * 普通失败提示信息
     */
    public static RestResult failed(Integer code, String message) {
        RestResult restResult = new RestResult();
        restResult.code = code;
        restResult.message = message;
        return restResult;
    }
    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public static RestResult validateFailed(String message) {
        RestResult restResult = new RestResult();
        restResult.code = VALIDATE_FAILED;
        restResult.message = message;
        return restResult;
    }

    /**
     * 未登录时使用
     *
     * @param message 错误信息
     */
    public static RestResult unauthorized(String message) {
        RestResult restResult = new RestResult();
        restResult.code = UNAUTHORIZED;
        restResult.message = "暂未登录或token已经过期";
        restResult.data = message;
        return restResult;
    }

    /**
     * 未授权时使用
     *
     * @param message 错误信息
     */
    public static RestResult forbidden(String message) {
        RestResult restResult = new RestResult();
        restResult.code = FORBIDDEN;
        restResult.message = "没有相关权限";
        restResult.data = message;
        return restResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}