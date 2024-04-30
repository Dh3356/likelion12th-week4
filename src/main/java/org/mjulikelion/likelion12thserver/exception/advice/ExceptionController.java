package org.mjulikelion.likelion12thserver.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.mjulikelion.likelion12thserver.dto.response.ErrorResponseDto;
import org.mjulikelion.likelion12thserver.exception.CustomException;
import org.mjulikelion.likelion12thserver.exception.DtoValidationException;
import org.mjulikelion.likelion12thserver.exception.errorcode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomException(
            CustomException customException) {
        this.writeLog(customException);
        HttpStatus httpStatus = this.resolveHttpStatus(customException);
        return new ResponseEntity<>(ErrorResponseDto.res(customException), httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
        if (fieldError == null) {
            return new ResponseEntity<>(ErrorResponseDto.res(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    methodArgumentNotValidException), HttpStatus.BAD_REQUEST);
        }

        ErrorCode validationErrorCode = ErrorCode.resolveValidationErrorCode(fieldError.getCode());
        String detail = fieldError.getDefaultMessage();
        DtoValidationException dtoValidationException = new DtoValidationException(validationErrorCode, detail);
        this.writeLog(dtoValidationException);
        return new ResponseEntity<>(ErrorResponseDto.res(dtoValidationException), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        this.writeLog(exception);
        return new ResponseEntity<>(
                ErrorResponseDto.res(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus resolveHttpStatus(CustomException customException) {
        return HttpStatus.resolve(Integer.parseInt(customException.getErrorCode().getCode().substring(0, 3)));
    }

    private void writeLog(CustomException customException) {
        String exceptionName = customException.getClass().getSimpleName();
        ErrorCode errorCode = customException.getErrorCode();
        String detail = customException.getDetail();

        log.error("({}){}: {}", exceptionName, errorCode.getMessage(), detail);
    }

    private void writeLog(Exception exception) {
        log.error("({}){}", exception.getClass().getSimpleName(), exception.getMessage());
    }
}
