package com.mall.common.exception;

public class JWTAuthException extends BaseException {
    private static final long serialVersionUID = -3784813826712798538L;

    public JWTAuthException(int status, String message) {
        super(status, message);
    }

    public JWTAuthException(String message) {
        super(message);
    }

    public JWTAuthException(BaseExceptionCode ec) {
        super(ec.getCode(), ec.getMessage());
    }
}
