package org.mjulikelion.likelion12thserver.exception;

import org.mjulikelion.likelion12thserver.exception.errorcode.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(String detail) {
        super(ErrorCode.NOT_FOUND, detail);
    }
}
