package com.mall.mallcommon.exception;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/26
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = -3784813826712798538L;
    private static final int DEFAULT_CODE = 40600;

    public BusinessException(int status, String message) {
        super(status, message);
    }

    public BusinessException(String message) {
        super(40600, message);
    }

    public BusinessException(BaseExceptionCode ec) {
        super(ec.getCode(), ec.getMessage());
    }
}