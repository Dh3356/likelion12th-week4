package org.mjulikelion.likelion12thserver.dto.response.memo;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12thserver.model.Memo;

@Getter
@Builder
public class MemoListResponseData {
    private final List<Memo> memoList;
}
