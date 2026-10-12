package org.example.exception;

import org.example.common.ErrorCode;

/**
 * 业务异常:Service 层抛出,由 GlobalExceptionHandler 统一处理
 */
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String detail) {
        super(errorCode.getMessage() + ":" + detail);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
