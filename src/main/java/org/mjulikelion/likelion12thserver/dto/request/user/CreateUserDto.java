package org.mjulikelion.likelion12thserver.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserDto {
    @NotBlank(message = "이름이 null이거나 비어있습니다.")
    private String name;
}
