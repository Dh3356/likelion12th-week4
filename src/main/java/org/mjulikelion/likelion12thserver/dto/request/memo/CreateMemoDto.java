package org.mjulikelion.likelion12thserver.dto.request.memo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMemoDto {
    @NotBlank(message = "제목이 null이거나 비어있습니다.")
    private String title;

    @NotBlank(message = "내용이 null이거나 비어있습니다.")
    private String content;
}
