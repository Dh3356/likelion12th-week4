package org.mjulikelion.likelion12thserver.dto.response.memo;

import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12thserver.model.Memo;

@Getter
@Builder
public class MemoResponseData {
    private final Memo memo;
    private final int likeCount;
}
