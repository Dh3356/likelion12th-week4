package org.mjulikelion.likelion12thserver.dto.response.memo;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12thserver.model.Like;

@Getter
@Builder
public class LikeListResponseData {
    private List<Like> likeList;
    private int likeCount;
}
