package org.mjulikelion.likelion12thserver.exception;

import lombok.Getter;
import org.mjulikelion.likelion12thserver.exception.errorcode.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String detail;

    public CustomException(ErrorCode errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public CustomException(ErrorCode errorCode) {
        this(errorCode, null);
    }
}
