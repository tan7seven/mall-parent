package com.mall.mallcommon.exception;


import com.mall.mallcommon.enums.ResultStatus;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 4242575188983511008L;
    private int status = 0;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseException() {
    }

    public BaseException(int status, String message) {
        super(message);
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseException(ResultStatus resultStatus) {
        super(resultStatus.getMessage());
        this.status = resultStatus.getCode();
    }
}