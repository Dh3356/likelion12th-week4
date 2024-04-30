package org.mjulikelion.likelion12thserver.exception;

import org.mjulikelion.likelion12thserver.exception.errorcode.ErrorCode;

public class ConflictException extends CustomException {
    public ConflictException(String detail) {
        super(ErrorCode.CONFLICT, detail);
    }
}
