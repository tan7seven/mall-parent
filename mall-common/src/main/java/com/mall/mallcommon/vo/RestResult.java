package com.mall.mallcommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.mallcommon.enums.ResultStatus;
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
    private static final long serialVersionUID = 1675844408998444857L;
    private T data;

    public RestResult(int status, String message) {
        super(status, message);
    }

    public RestResult(T data) {
        this.data = data;
        this.setStatus(200);
        this.setMessage("SUCCESS");
    }

    public static <E> RestResult<E> timeout() {
        return fail("请求超时，请稍候再试");
    }

    public static RestResult success() {
        return new RestResult(200, "SUCCESS");
    }

    public static RestResult success(String message) {
        return new RestResult(200, message);
    }

    public static <E> RestResult<E> success(E data) {
        return new RestResult(data);
    }

    public static RestResult success(int status, String message) {
        return new RestResult(status, message);
    }

    public static <E> RestResult<E> success(String message, E data) {
        RestResult<E> restResult = new RestResult();
        restResult.setStatus(200);
        restResult.setMessage(message);
        restResult.setData(data);
        return restResult;
    }

    public static <E> RestResult<E> fail(int status, String msg) {
        return new RestResult(status, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试");
    }

    public static <E> RestResult<E> fail(String msg) {
        return fail(FAIL, msg);
    }


    public static <E> RestResult<E> fail(ResultStatus resultStatus) {
        return fail(resultStatus.getCode(), resultStatus.getMessage());
    }


}