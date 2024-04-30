package org.mjulikelion.likelion12thserver.exception;

import org.mjulikelion.likelion12thserver.exception.errorcode.ErrorCode;

public class ForbiddenException extends CustomException {
    public ForbiddenException(String detail) {
        super(ErrorCode.FORBIDDEN, detail);
    }
}
