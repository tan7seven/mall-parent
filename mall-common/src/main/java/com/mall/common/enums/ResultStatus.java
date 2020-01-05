package com.mall.common.enums;

import com.mall.common.exception.BaseExceptionCode;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
public enum ResultStatus implements BaseExceptionCode {
    SUCCESS(200, "SUCCESS"),
    FAIL(500, "FAIL"),
    FORBIDDEN(403, "请求被拒绝"),
    REQUEST_TIMEOUT(408, "业务繁忙，请稍候再试!"),
    BAD_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    NOT_FOUND(404, "数据不存在"),
    RESUBMIT(101, "您的请求正在处理，请稍候再试。"),
    INTERNAL_SERVER_ERROR(500, "服务异常，请稍候再试。");

    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResultStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}