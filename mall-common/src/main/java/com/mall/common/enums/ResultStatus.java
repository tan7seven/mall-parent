package com.mall.common.enums;

import com.mall.common.exception.BaseExceptionCode;
import lombok.Getter;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
@Getter
public enum ResultStatus implements BaseExceptionCode {
    /**
     * 请求成功
     */
    SUCCESS(200, "success"),
    SUCCESS_CREATED(201, "已创建"),
    SUCCESS_RECEIVE(202, "接收"),
    SUCCESS_ILLEGALITY_MSG(203, "非认证信息"),
    /**
     * 客户方错误
     */
    CLIENT_BAD_REQUEST(400, "请求失败"),
    CLIENT_NEED_APPROVE(401, "未认证"),
    CLIENT_NEED_PAY(402, "需要付费"),
    CLIENT_FORBIDDEN(403, "禁止"),
    CLIENT_NOT_FOUND(404, "未找到"),
    CLIENT_NOT_AGREE(405, "不允许"),
    CLIENT_NOT_RECEIVE(406, "不接受"),
    CLIENT_NOT_AGENCY(407, "需要代理认证"),
    CLIENT_TIMEOUT(408, "请求超时"),
    CLIENT_CONFLICT(409, "请求冲突"),
    CLIENT_FAILED(410, "请求失败"),
    CLIENT_LENGTH_REQUIRED(411, "请求长度不够"),
    /**
     * 服务方错误
     */
    SERVER_ERROR(500, "服务异常"),

    /**
     * 自定义业务异常
     */
    BUS_MSG_NOT_FOUND(1000, "数据不存在"),
    BUS_RESUBMIT(1001, "您的请求正在处理，请稍候再试"),
    /**
     * 自定义用户权限异常
     */
    USER_LOGIN_SUCCESS(2000,"用户登录成功"),
    USER_NEED_AUTHORITIES(2001,"用户未登录"),
    USER_LOGIN_FAILED(2002,"用户账号或密码错误"),
    USER_NO_ACCESS(2003,"没有相关权限"),
    USER_LOGOUT_SUCCESS(2004,"用户登出成功"),
    USER_IS_BLACKLIST(2005,"此token为黑名单"),
    USER_IS_OVERDUE(2006,"登录已失效"),

    ;

    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    ResultStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}