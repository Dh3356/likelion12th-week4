package org.mjulikelion.likelion12thserver.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND("4040", "존재하지 않는 데이터입니다."),
    CONFLICT("4090", "이미 존재하는 데이터입니다."),
    FORBIDDEN("4030", "해당 리소스에 대한 접근 권한이 없습니다."),

    DTO_NOT_BLANK("9000", "필수값이 빈 값이거나 공백으로 되어있습니다.");

    private final String code;
    private final String message;

    public static ErrorCode resolveValidationErrorCode(String code) {
        return switch (code) {
            case "NotBlank" -> DTO_NOT_BLANK;
            default -> throw new IllegalArgumentException("Unexpected value: " + code);
        };
    }
}
