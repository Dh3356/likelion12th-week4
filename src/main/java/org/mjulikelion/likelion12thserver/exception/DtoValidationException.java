package org.mjulikelion.likelion12thserver.exception;

import org.mjulikelion.likelion12thserver.exception.errorcode.ErrorCode;

public class DtoValidationException extends CustomException {
    public DtoValidationException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
