package com.mall.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.common.enums.ResultStatus;
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
        restResult.code = ResultStatus.HANDLE_FAILED.getCode();
        restResult.message = ResultStatus.HANDLE_FAILED.getMessage();
        return restResult;
    }
    /**
     * 普通失败提示信息
     */
    public static RestResult failed(String message) {
        RestResult restResult = new RestResult();
        restResult.code = ResultStatus.HANDLE_FAILED.getCode();
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
     * 普通失败提示信息
     */
    public static RestResult failed(ResultStatus resultStatus) {
        RestResult restResult = new RestResult();
        restResult.code = resultStatus.getCode();
        restResult.message = resultStatus.getMessage();
        return restResult;
    }
    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public static RestResult validateFailed(String message) {
        RestResult restResult = new RestResult();
        restResult.code = ResultStatus.PARAM_FAILED.getCode();
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
        restResult.code = ResultStatus.CLIENT_NEED_APPROVE.getCode();
        restResult.message = "暂未登录或token已经过期";
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